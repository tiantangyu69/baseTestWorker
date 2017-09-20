import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.SimpleResourceHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by bjlizhitao on 2016/6/15.
 */
@ContextConfiguration(locations = {"classpath:spring-rabbitmq-consumer.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class TestBatchConsumeMsg {
    @Resource(name = "connectionFactory1")
    private ConnectionFactory connectionFactory;

    @Test
    public void testConsumer() {

        try {
            Thread.sleep(20000);
            System.out.println("======================================================");

            SimpleResourceHolder.bind("factory1", connectionFactory);

            Thread.sleep(5000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
