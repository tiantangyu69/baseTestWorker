package guava.collect;

import com.google.common.collect.ArrayListMultimap;
import org.junit.Test;

import java.util.Map;

/**
 * Created by bjlizhitao on 2017/4/11.
 */
public class ArrayListMultimapTest {
    @Test
    public void test() {
        ArrayListMultimap<String, String> arrayListMultimap = ArrayListMultimap.create();
        arrayListMultimap.put("aaa", "aaa");
        arrayListMultimap.put("aaa", "bbb");
        arrayListMultimap.put("aaa", "ccc");

        for (Map.Entry<String, String> entry : arrayListMultimap.entries()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}
