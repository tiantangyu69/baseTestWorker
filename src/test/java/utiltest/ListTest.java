package utiltest;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bjlizhitao on 2016/11/15.
 */
public class ListTest {
    @Test
    public void testRemove() {
        List<String> list = new ArrayList<String>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");
        list.add("f");
        list.add("g");

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals("a") || list.get(i).equals("c"))
                list.remove(list.get(i));
            System.out.println(list.get(i));
        }

        System.out.println(list);
    }
}
