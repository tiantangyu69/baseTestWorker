import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

import javax.annotation.Resource;
import java.util.Set;

/**
 * Created by bjlizhitao on 2016/6/15.
 */
@ContextConfiguration(locations = {"classpath:spring-rabbitmq-producer.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class TestBatchSendMsgToFollowers {
    @Resource
    private AmqpTemplate AmqpTemplate;

    @Test
    public void testBatchInsertAndGetFormRedis() {

        Jedis jedis = new Jedis();

        Pipeline pipeline = jedis.pipelined();
        long beginTime = System.currentTimeMillis();
        for (int i = 0; i < 200000; i++) {
            pipeline.zadd("myfollowers", i, "followers" + i);
        }
        pipeline.sync();
        long endTime = System.currentTimeMillis();

        System.out.println("批量插入完成，耗时：" + (endTime - beginTime) + "毫秒");

        beginTime = System.currentTimeMillis();
        Set<String> myfollowers = jedis.zrange("myfollowers", 0, -1);


        endTime = System.currentTimeMillis();
        System.out.println("批量获取" + myfollowers.size() + "条数据完成，耗时：" + (endTime - beginTime) + "毫秒");


        beginTime = System.currentTimeMillis();
        for (String myfollower: myfollowers) {
            AmqpTemplate.convertAndSend("queue_one_key", myfollower);
        }
        endTime = System.currentTimeMillis();
        System.out.println("发送消息到rabbitMQ完成，发送消息数：" + myfollowers.size() + "条，耗时：" + (endTime - beginTime) + "毫秒");
        /*try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }
}
