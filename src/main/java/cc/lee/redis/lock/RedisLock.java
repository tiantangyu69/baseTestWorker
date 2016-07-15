package cc.lee.redis.lock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by bjlizhitao on 2016/6/29.
 */
public class RedisLock {
    private Jedis jedis = new Jedis("127.0.0.1", 6379);
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisLock.class);

    /**
     * 默认等待获取锁超时时间（毫秒），这里为10000毫秒，即10秒
     */
    private static final long DEFAULT_WAIT_TIME = 10000;

    /**
     * 锁失效时间（毫秒），过期删除，这里为60秒
     */
    private static final int LOCK_EXPIRE_TIME = 60;

    /**
     * 尝试获取锁,不管是否获取到锁立即返回获取状态
     *
     * @param lockKey
     * @return
     */
    public boolean tryLock(String lockKey) {
        try {
            // 锁失效时间60秒
            String lockExpireTimeStr = getLockExpireTimeStr();
            return doGetLock(lockKey, lockExpireTimeStr);
        } catch (Exception e) {
            LOGGER.error("RedisLock.tryLock() Exception:" + e.getMessage());
            unlock(lockKey);// 出异常时删除缓存key
            return false;
        }
    }

    /**
     * 循环获取锁，使用默认的获取锁超时时间 10秒
     *
     * @param lockKey 锁名称
     */
    public void lock(String lockKey) {
        lock(lockKey, DEFAULT_WAIT_TIME, TimeUnit.MILLISECONDS);
    }

    /**
     * 循环获取锁，直到超时
     *
     * @param lockKey
     * @param waitTime 超时时间
     * @param timeUnit 超时时间单位
     */
    public void lock(String lockKey, long waitTime, TimeUnit timeUnit) {
        try {
            long currentNanoTime = System.nanoTime();
            long waitTimeoutNano = timeUnit.toNanos(waitTime);// 获取锁等待超时时间默认10秒

            // 锁失效时间60秒
            String lockExpireTimeStr = getLockExpireTimeStr();

            while ((System.nanoTime() - currentNanoTime) < waitTimeoutNano) {// 未获取到则重试
                if (doGetLock(lockKey, lockExpireTimeStr)) {// 获取到锁
                    return;
                } else {
                    Thread.sleep(100);//未获取到锁短暂休眠，并继续尝试获取锁
                }
            }
        } catch (Exception e) {
            LOGGER.error("RedisLock.lock() Exception:" + e.getMessage());
            unlock(lockKey);
            throw new RuntimeException(String.format("get lock %s failed", lockKey));
        }
        //已超出加锁的最长等待时间
        throw new RuntimeException(String.format("get lock %s time out", lockKey));
    }

    /**
     * 获取锁失效时间字符串，失效时间为当前时间加60秒
     *
     * @return
     */
    private String getLockExpireTimeStr() {
        return String.valueOf(System.currentTimeMillis() + (LOCK_EXPIRE_TIME * 1000));
    }

    /**
     * 执行获取锁操作
     *
     * @param lockKey
     * @param lockExpireTimeStr
     * @return
     */
    private boolean doGetLock(String lockKey, String lockExpireTimeStr) {
        long setNxResult = jedis.setnx(lockKey, lockExpireTimeStr);
        if (setNxResult == 1) {// 获取到锁
            jedis.expire(lockKey, LOCK_EXPIRE_TIME);// 设置锁失效时间60秒
            return true;
        }

        String currentLockExpireTimeStr = jedis.get(lockKey);// 获取缓存中设置的锁的过期时间
        // 检查锁是否过期，这里主要是防止setNx命令执行后redis崩溃未执行expire操作造成死锁
        if (null != currentLockExpireTimeStr && Long.parseLong(currentLockExpireTimeStr) < System.currentTimeMillis()) {
            String oldValue = jedis.getSet(lockKey, lockExpireTimeStr);// 获取设置缓存之前的时间戳
            // 防止锁过期后，多个线程并发获取到锁，通过对比获取的时间戳和设置时的时间戳是否相同判断是否同一线程获取到锁
            if (null != oldValue && oldValue.equals(currentLockExpireTimeStr)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 释放锁
     *
     * @param lockKey
     */
    public void unlock(String lockKey) {
        jedis.del(lockKey);
    }

    /**
     * 在redis锁中执行业务逻辑
     *
     * @param lockKey
     */
    public <T> T doInLock(String lockKey, LockExecutor<T> executor) {
        return doInLock(lockKey, DEFAULT_WAIT_TIME, TimeUnit.MILLISECONDS, executor);
    }

    /**
     * 在redis锁中执行业务逻辑
     *
     * @param lockKey
     * @param waitTime
     * @param timeUnit
     */
    public <T> T doInLock(String lockKey, long waitTime, TimeUnit timeUnit, LockExecutor<T> executor) {
        try {
            lock(lockKey, waitTime, timeUnit);
            T result = executor.execute();
            unlock(lockKey);
            return result;
        } catch (Exception e) {
            LOGGER.error("RedisLock.doInLock() Exception:" + e.getMessage());
            throw new RuntimeException("RedisLock.doInLock() Exception" + e.getMessage());
        }
    }

    /**
     * 获取锁之后的业务逻辑执行器，用于执行代码逻辑
     */
    public interface LockExecutor<T> {
        T execute();
    }

    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(10);

        final String lockName = "lock";

        for (int i = 0; i < 100; i++) {
            threadPool.execute(new Runnable() {
                public void run() {
                    RedisLock redisLock = new RedisLock();
                    /*if (redisLock.tryLock(lockName)) {
                        System.out.println(Thread.currentThread().getName() + " 获取到锁");
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        redisLock.unlock(lockName);
                    } else {

                    }*/

                    /*redisLock.lock(lockName);
                    System.out.println(Thread.currentThread().getName() + " 获取到锁");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    redisLock.unlock(lockName);*/

                    redisLock.doInLock(lockName, new LockExecutor<Void>() {
                        public Void execute() {
                            System.out.println(Thread.currentThread().getName() + " 获取到锁");
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            return null;
                        }
                    });
                }
            });
        }
        threadPool.shutdown();
    }
}
