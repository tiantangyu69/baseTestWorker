package redis;

import com.google.common.base.Joiner;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

import java.util.Date;
import java.util.UUID;

/**
 * Created by bjlizhitao on 2016/7/5.
 * 测试关注列表、粉丝列表、feed流占redis内存大小
 * 每个用户200关注数、1200条feed流，粉丝数随机，总的关注数=总的粉丝数两者占用相同内存
 */
public class TestFollowRedisMemoryUsed {
    // 关注列表key
    public static final String FOLLOWLIST_CACHE_KEY = "follow_list_";
    // 粉丝列表key
    public static final String FOLLOWERLIST_CACHE_KEY = "follower_list_";
    // feed流key
    public static final String FEED_CACHE_KEY = "feed_list_";

    public static final int CONNECTION_TIMEOUT = 3000;// redis 连接超时3秒
    public static final int SO_TIMEOUT = 300000;// so_timeout 5分钟
    public static final String HOST = "localhost";
    public static final int PORT = 6379;


    /**
     * 1000用户内存占用，约195MB
     */
    @Test
    public void test1000User() {
        doTest(1);
    }

    /**
     * 5000用户内存占用，约980MB
     */
    @Test
    public void test5000User() {
        doTest(5);
    }

    /**
     * 10000用户内存占用，约1.91GB
     */
    @Test
    public void test10000User() {
        doTest(10);
    }

    /**
     * 数据量，单位为千条
     *
     * @param countThousand
     */
    private void doTest(int countThousand) {
        Jedis jedis = new Jedis(HOST, PORT, CONNECTION_TIMEOUT, SO_TIMEOUT);

        for (int a = 0; a < countThousand; a++) {
            for (int i = a * 1000; i < (a + 1) * 1000; i++) {// 1000个用户
                // 以线上数据为基准
                long userIdStart = 10000000L;// 用户id，从 10000000L 开始
                long commentIdStart = 2500000000L;// commentId，从2500000000L开始

                Pipeline pipeline = jedis.pipelined();

                follow(pipeline, userIdStart, userIdStart + 200000);// 添加关注和粉丝

                addFeed(pipeline, userIdStart + i, commentIdStart, userIdStart + 200000 + i);// 添加feed流

                pipeline.sync();
            }
        }
    }

    private void follow(Pipeline pipeline, Long userId, Long followUserId) {
        for (int k = 0; k < 200; k++) {//添加关注，每人200条
            long f = followUserId + k;
            pipeline.zadd(FOLLOWLIST_CACHE_KEY + userId, new Date().getTime(), String.valueOf(f));
            // 粉丝列表响应添加200条
            pipeline.zadd(FOLLOWERLIST_CACHE_KEY + f, new Date().getTime(), String.valueOf(userId));
        }
    }

    private void addFeed(Pipeline pipeline, Long userId, Long commentId, Long commentUserId) {
        for (int j = 0; j < 1200; j++) {// 添加feed流，每人1200条
            pipeline.zadd(FEED_CACHE_KEY + userId, new Date().getTime(), Joiner.on("_").join(UUID.randomUUID().toString().substring(0, 16), commentId + j, commentUserId + 100000 + j));
        }
    }
}
