package guava.base;

import com.google.common.base.Stopwatch;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by bjlizhitao on 2017/4/11.
 */
public class StopWatchTest {
    @Test
    public void test() throws InterruptedException {
        Stopwatch stopwatch = Stopwatch.createStarted();
        System.out.println("stop is running: " + stopwatch.isRunning());
        Thread.sleep(1000);
        stopwatch.stop();
        System.out.println("test elapsed milliseconds is: " + stopwatch.elapsed(TimeUnit.MILLISECONDS));
        System.out.println("stop is running: " + stopwatch.isRunning());

        stopwatch.reset();
        stopwatch.start();
        Thread.sleep(500);
        stopwatch.stop();
        System.out.println("test elapsed milliseconds is: " + stopwatch.elapsed(TimeUnit.MILLISECONDS));

    }
}
