package guava.base;

import com.google.common.base.CaseFormat;
import org.junit.Test;

/**
 * Created by bjlizhitao on 2017/3/31.
 */
public class CaseFormatTest {
    @Test
    public void test() {
        System.out.println(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_CAMEL, "aaaaaaaaaaBBBddDD"));
    }
}
