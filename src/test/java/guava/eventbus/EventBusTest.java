/*
package guava.eventbus;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import org.junit.Test;

*/
/**
 * Created by bjlizhitao on 2017/4/11.
 *//*

public class EventBusTest {

    @Test
    public void test() {
        EventBus eventBus = new EventBus("testEventBus");

        System.out.println(eventBus.identifier());

        eventBus.register(new Object() {
            @Subscribe
            public void lister(Integer integer) {
                System.out.printf("%s from int%n", integer);
            }

            @Subscribe
            public void lister(Number integer) {
                System.out.printf("%s from Number%n", integer);
            }

            @Subscribe
            public void lister(Long integer) {
                System.out.printf("%s from long%n", integer);
            }
        });


        eventBus.post(1);
        eventBus.post(2L);

        eventBus.post(new Object());
    }
}
*/
