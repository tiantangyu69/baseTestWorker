package guava.base;

import static com.google.common.base.Preconditions.*;

import org.junit.Test;

/**
 * Created by bjlizhitao on 2016/6/28.
 */
public class PreconditionsTest {
    @Test
    public void test() {
        int i = 1;
        checkArgument(i >= 0, "Argument was %s but expected nonnegative", i);

        Integer count = 1;
        checkNotNull(count, "count can not be null");

        checkState(count == 1, "illegal count state %s", count);


    }
}
