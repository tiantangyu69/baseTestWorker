package guava.base;

import com.google.common.base.Optional;
import org.junit.Test;

/**
 * Created by bjlizhitao on 2016/6/27.
 */
public class OptionalTest {
    @Test
    public void testOptional() {
        Optional<Integer> possible = Optional.of(11);
        System.out.println(possible.isPresent());
        System.out.println(possible.get());

        possible = Optional.absent();
        System.out.println(possible.isPresent());
//        System.out.println(possible.get());
        System.out.println(possible.or(222222222));
        System.out.println(possible.orNull());

        possible = Optional.fromNullable(22);
        System.out.println(possible.isPresent());
        System.out.println(possible.get());

    }
}
