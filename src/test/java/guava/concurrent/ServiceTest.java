package guava.concurrent;

import com.google.common.util.concurrent.AbstractExecutionThreadService;
import com.google.common.util.concurrent.AbstractIdleService;
import org.junit.Test;

/**
 * Created by bjlizhitao on 2016/6/28.
 */
public class ServiceTest {
    @Test
    public void test() {

    }

    private class Service1 extends AbstractIdleService {
        @Override
        protected void startUp() throws Exception {
            System.out.println("start");
        }

        @Override
        protected void shutDown() throws Exception {
            System.out.println("shutDown");
        }
    }

    private class Service2 extends AbstractExecutionThreadService {
        @Override
        protected void run() throws Exception {
            System.out.println("run");
        }
    }
}
