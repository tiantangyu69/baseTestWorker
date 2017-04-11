package guava.collect;

import com.google.common.collect.EvictingQueue;
import org.junit.Test;

/**
 * Created by bjlizhitao on 2017/4/11.
 */
public class EvictingQueueTest {
    @Test
    public void test(){
        EvictingQueue<String> evictingQueue = EvictingQueue.create(3);

        evictingQueue.add("aa");
        evictingQueue.add("bb");
        evictingQueue.add("cc");
        evictingQueue.add("dd");

        System.out.println(evictingQueue.peek());
        System.out.println(evictingQueue.peek());

        System.out.println(evictingQueue.poll());
        System.out.println(evictingQueue.poll());
        System.out.println(evictingQueue.poll());
        System.out.println(evictingQueue.poll());

        evictingQueue.offer("aa");
        evictingQueue.offer("bb");
        evictingQueue.offer("cc");
        evictingQueue.offer("dd");


        System.out.println(evictingQueue.poll());
        System.out.println(evictingQueue.poll());
        System.out.println(evictingQueue.poll());
        System.out.println(evictingQueue.poll());

    }
}
