package redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by bjlizhitao on 2017/5/8.
 */
@ContextConfiguration(locations = {"classpath:spring-redis.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class RedisTransactionTest {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    public void testTransaction() {
        final String key = "transaction";
        if (redisTemplate.hasKey(key)) {
            System.out.println("has key");
            return;
        }

        Boolean success = redisTemplate.execute(new SessionCallback<Boolean>() {
            @Override
            public Boolean execute(RedisOperations operations) throws DataAccessException {
                try {
                    operations.multi();
                    BoundValueOperations<String, Object> valueOperations = operations.boundValueOps(key);

                    valueOperations.setIfAbsent("test");
                    valueOperations.expire(100, TimeUnit.SECONDS);

                    List<Object> result = operations.exec();
                    return (Boolean) result.get(0);
                } catch (Exception e) {
                    return false;
                }
            }
        });

        System.out.println("execute transaction success? " + success);
    }
}
