package guava.collect;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by bjlizhitao on 2017/4/11.
 */
public class Collections2Test {
    private List<String> list = new ArrayList();

    {
        list.add("a");
        list.add("f");
        list.add("g");
        list.add("d");
        list.add("b");
        list.add("c");
        list.add("e");
    }

    @Test
    public void testFilter() {
        Collection<String> filtedList = Collections2.filter(list, new Predicate<String>() {
            public boolean apply(String input) {
                if (input.equals("b") || input.equals("f"))
                    return false;

                return true;
            }
        });

        for (String s : filtedList) {
            System.out.println(s);
        }
    }

    @Test
    public void test() {
        Collection<List<String>> orderedList = Collections2.orderedPermutations(list);

        for (List<String> list : orderedList) {
            System.out.println(Joiner.on(",").join(list));
        }
    }

    @Test
    public void testTransform() {
        Collection<String> transformList = Collections2.transform(list, new Function<String, String>() {
            public String apply(String input) {
                return "this is: " + input;
            }
        });

        for (String s : transformList) {
            System.out.println(s);
        }
    }
}
