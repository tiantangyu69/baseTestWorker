package guava.concurrent;

import com.google.common.util.concurrent.RateLimiter;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by bjlizhitao on 2017/4/11.
 */
public class RateLimiterTest {
    RateLimiter rateLimiter = RateLimiter.create(5);
    RateLimiter rateLimiter2 = RateLimiter.create(5, 2, TimeUnit.SECONDS);
    ExecutorService threadPool = Executors.newFixedThreadPool(10);

    @Test
    public void test() throws InterruptedException {
        for (int i = 0; i < 20; i++) {
            threadPool.execute(new Runnable() {
                public void run() {
                    System.out.println(rateLimiter.acquire());
                }
            });
        }

        Thread.sleep(5000);
        threadPool.shutdown();
    }

    @Test
    public void test2() {
        for (int i = 0; i < 20; i++) {
            System.out.println(rateLimiter.acquire());
        }
    }

    @Test
    public void testWarmup() {
        for (int i = 0; i < 20; i++) {
            System.out.println(rateLimiter2.acquire());
        }
    }
}
