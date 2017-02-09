package cc.lee.spring.rabbit;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;

import java.util.concurrent.CountDownLatch;

public class QueueTwoLitener implements MessageListener {

    public void onMessage(Message message) {
        System.out.println("queue 2 mesage");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
