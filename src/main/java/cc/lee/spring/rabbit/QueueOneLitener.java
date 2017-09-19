package cc.lee.spring.rabbit;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;

public class QueueOneLitener implements MessageListener {
//    public static final java.util.concurrent.CountDownLatch CountDownLatch = new CountDownLatch(3000);

    public void onMessage(Message message) {
        Jackson2JsonMessageConverter jmc = new Jackson2JsonMessageConverter();
        String follower = (String) jmc.fromMessage(message);
        System.out.println("follwer :" + follower);
//        CountDownLatch.countDown();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
