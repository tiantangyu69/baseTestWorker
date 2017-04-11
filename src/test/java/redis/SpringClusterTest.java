package redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.JedisCluster;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by bjlizhitao on 2017/3/20.
 */
@ContextConfiguration(locations = {"classpath:spring-redis.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class SpringClusterTest {
    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Test
    public void testSet() {
        redisTemplate.opsForValue().set("aaaa", "bbbb");
        redisTemplate.expire("aaaa", 10, TimeUnit.SECONDS);

        System.out.println(redisTemplate.opsForValue().get("aaaa"));

        redisTemplate.delete("aaaa");
    }

    @Test
    public void testZadd() {
        Set<ZSetOperations.TypedTuple<String>> tuples = new HashSet<ZSetOperations.TypedTuple<String>>();
        tuples.add(new DefaultTypedTuple("1", 1d));
        tuples.add(new DefaultTypedTuple("2", 2d));

        JedisCluster cluster = (JedisCluster) redisTemplate.getConnectionFactory().getClusterConnection().getNativeConnection();

//        cluster.sadd();
    }

    @Test
    public void testPipeline(){
        redisTemplate.executePipelined(new RedisCallback<String>() {
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                connection.set(redisTemplate.getStringSerializer().serialize("aaaaaaaaa"), redisTemplate.getStringSerializer().serialize("ccccccccccccccccc"));
                return null;
            }
        });
    }

}
