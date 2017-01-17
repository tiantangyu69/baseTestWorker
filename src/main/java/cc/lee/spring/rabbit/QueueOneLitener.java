package cc.lee.spring.rabbit;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;

import java.util.concurrent.CountDownLatch;

public class QueueOneLitener implements MessageListener {
    public static final java.util.concurrent.CountDownLatch CountDownLatch = new CountDownLatch(1);

    public void onMessage(Message message) {
        Jackson2JsonMessageConverter jmc = new Jackson2JsonMessageConverter();
        String follower = (String) jmc.fromMessage(message);
        System.out.println("follwer :" + follower);
        CountDownLatch.countDown();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
