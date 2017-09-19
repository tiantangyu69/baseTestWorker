import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by bjlizhitao on 2016/6/15.
 */
@ContextConfiguration(locations = {"classpath:spring-rabbitmq-consumer.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class TestBatchConsumeMsg {

    @Test
    public void testConsumer() {

        try {
            Thread.sleep(500000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
