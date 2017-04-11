package guava.base;

import com.google.common.base.Joiner;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import org.junit.Test;

/**
 * Created by bjlizhitao on 2016/6/28.
 */
public class ObjectsTest {
    @Test
    public void testEqual() {
        System.out.println(Objects.equal("a", "a"));
        System.out.println(Objects.equal("a", "b"));
        System.out.println(Objects.equal("a", null));
        System.out.println(Objects.equal(null, "a"));
        System.out.println(Objects.equal(null, null));
    }

    @Test
    public void testHashCode(){
        System.out.println(Objects.hashCode("a"));
        System.out.println(Objects.hashCode("a"));
        System.out.println(Objects.hashCode("a", "b"));
        System.out.println(Objects.hashCode("a", "b"));
        System.out.println(Objects.hashCode("a", "b", 1));
        System.out.println(Objects.hashCode("a", "b", 2));
    }

    @Test
    public void testToString(){
        System.out.println(MoreObjects.toStringHelper(ObjectsTest.class).add("x", 1).add("y", "aaa"));
        System.out.println(MoreObjects.toStringHelper(this).toString());
    }
}
