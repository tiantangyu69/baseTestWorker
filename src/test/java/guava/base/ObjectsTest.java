package guava.base;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;
import org.junit.Test;

/**
 * Created by bjlizhitao on 2016/6/28.
 */
public class ObjectsTest {
    @Test
    public void test() {
        System.out.println(Objects.equal("a", "a"));
        System.out.println(Objects.equal("a", "b"));
        System.out.println(Objects.equal("a", null));
        System.out.println(Objects.equal(null, "a"));
        System.out.println(Objects.equal(null, null));

        System.out.println(Objects.hashCode("a"));
        System.out.println(Objects.hashCode("a"));
        System.out.println(Objects.hashCode("a", "b"));
        System.out.println(Objects.hashCode("a", "b"));
        System.out.println(Objects.hashCode("a", "b", 1));
        System.out.println(Objects.hashCode("a", "b", 2));

//        Objects.toStringHelper() is Deprecated
        System.out.println(MoreObjects.toStringHelper(ObjectsTest.class).add("x", 1).add("y", "aaa"));
    }
}

class Person implements Comparable<Person> {
    private String lastName;
    private String firstName;
    private int age;

    public int compareTo(Person another) {
        int cmp = lastName.compareTo(another.lastName);
        if (cmp != 0)
            return cmp;
        cmp = firstName.compareTo(another.firstName);
        if (cmp != 0) {
            return cmp;
        }

        return Integer.compare(age, another.age);
    }

    public int compareTo2(Person another) {
        return ComparisonChain.start()
                .compare(this.lastName, another.lastName)
                .compare(this.firstName, another.firstName)
                .compare(this.age, another.age)
                .result();
    }
}
