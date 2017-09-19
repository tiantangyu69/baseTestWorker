package redis;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Stopwatch;
import com.google.common.collect.Maps;
import io.netty.util.internal.ThreadLocalRandom;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by bjlizhitao on 2016/11/30.
 */
public class RedisZsetTest {
    public static final int CONNECTION_TIMEOUT = 3000;// redis 连接超时3秒
    public static final int SO_TIMEOUT = 300000;// so_timeout 5分钟
    public static final String HOST = "localhost";
    public static final int PORT = 6379;
    ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();


    @Test
    public void testZrank() {
        Jedis jedis = new Jedis(HOST, PORT, CONNECTION_TIMEOUT, SO_TIMEOUT);
        Stopwatch stopwatch = Stopwatch.createStarted();
        for (int a = 0; a < 200000; a++) {
            jedis.zcount("comment_rank", 0, Integer.MAX_VALUE);


//            System.out.println(jedis.zrank("comment_rank", String.valueOf(20001110 + a)));
            jedis.zincrby("comment_rank", 1, String.valueOf(20001110 + a));

//            Pipeline pipeline = jedis.pipelined();
//            pipeline.zadd("comment_rank", threadLocalRandom.nextInt(1000000), String.valueOf(20001110 + a));
//            pipeline.sync();
        }
        stopwatch.stop();
        long time = stopwatch.elapsed(TimeUnit.MILLISECONDS);
        System.out.println("总耗时：" + time + "; 平均耗时：" + (time / 200000.0));
    }

    @Test
    public void testZincreBy() {

    }

    @Test
    public void testUsedMemory() {
        Jedis jedis = new Jedis(HOST, PORT, CONNECTION_TIMEOUT, SO_TIMEOUT);


        for (int i = 0; i < 10000; i++) {
            Map<String, String> datas = Maps.newHashMap();
            datas.put("audioLock", "1");
            datas.put("cmtAgainst", "2967");
            datas.put("cmtVote", "203544");
            datas.put("createTime", "0");
            datas.put("docId", "CMNCCHHG0001875P");
            datas.put("needCheck", "0");
            datas.put("plock", "0");
            datas.put("rcount", "6244");
            datas.put("tcount", "6099");
            datas.put("threadAgainst", "0");
            datas.put("threadVote", "482");

            jedis.set("key" + i, JSON.toJSONString(datas)); // 2.87 mb
//            jedis.hmset("map" + i, datas); // 2.56 mb
        }
        jedis.close();
    }
}
