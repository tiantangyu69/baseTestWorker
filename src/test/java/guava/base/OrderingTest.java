package guava.base;

import com.google.common.base.Function;
import com.google.common.collect.Ordering;
import com.google.common.primitives.Ints;
import org.junit.Test;

/**
 * Created by bjlizhitao on 2016/6/28.
 */
public class OrderingTest {
    @Test
    public void test() {
        Ordering<String> byLengthOrdering = new Ordering<String>() {
            @Override
            public int compare(String left, String right) {
                return Ints.compare(left.length(), right.length());
            }
        };

        Ordering<Foo> ordering = Ordering.natural().nullsFirst().onResultOf(new Function<Foo, Comparable>() {
            public Comparable apply(Foo foo) {
                return foo.sortedBy;
            }
        });
    }
}

class Foo {
    String sortedBy;
}