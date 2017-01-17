import cc.lee.spring.rabbit.QueueOneLitener;
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
    public void testBatchInsertAndGetFormRedis() {
        try {
            long beginTime = System.currentTimeMillis();
            QueueOneLitener.CountDownLatch.await();
            long endTime = System.currentTimeMillis();
            System.out.printf("消耗消息耗时：" + (endTime - beginTime) + "毫秒");

            try {
                Thread.sleep(500000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
