package redis;

import com.google.common.base.Stopwatch;
import io.netty.util.internal.ThreadLocalRandom;
import org.junit.Test;
import redis.clients.jedis.Jedis;

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
}
