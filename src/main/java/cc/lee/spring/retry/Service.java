package cc.lee.spring.retry;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;

@org.springframework.stereotype.Service
public class Service {
    @Retryable(value = RuntimeException.class, maxAttempts = 5, backoff = @Backoff(delay = 1000, maxDelay = 3000))
    public void service() {
        System.out.println("==============service");
        throw new RuntimeException("test");
    }

    @Recover
    public void recover(Exception e) {
        System.out.println("recover 2");
    }

    @Recover
    public void recover(RuntimeException e) {
        System.out.println("recover 1");
    }
}