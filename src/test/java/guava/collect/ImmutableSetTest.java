package guava.collect;

import com.google.common.collect.ImmutableSet;
import org.junit.Test;

import java.util.Set;

/**
 * Created by bjlizhitao on 2016/6/28.
 */
public class ImmutableSetTest {
    public static final Set<String> COLOR_NAMES = ImmutableSet.of("red", "orange", "yellow", "green", "blue", "purple");
    public static final Set<Object> SET = ImmutableSet.builder().add("aaa").add("bbb").build();

    @Test
    public void test() {
        for (Object element : SET) {
            System.out.println(element);
        }

        for (String color : COLOR_NAMES) {
            System.out.println(color);
        }

        COLOR_NAMES.remove("red");
    }
}
