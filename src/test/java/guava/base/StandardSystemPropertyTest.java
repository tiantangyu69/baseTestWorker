package guava.base;

import com.google.common.base.StandardSystemProperty;
import org.junit.Test;

/**
 * Created by bjlizhitao on 2017/4/1.
 */
public class StandardSystemPropertyTest {
    @Test
    public void test(){
        System.out.println(StandardSystemProperty.JAVA_VERSION);
        System.out.println(StandardSystemProperty.FILE_SEPARATOR.value());
        System.out.println(StandardSystemProperty.JAVA_EXT_DIRS);
        System.out.println(StandardSystemProperty.JAVA_COMPILER);
        System.out.println(StandardSystemProperty.JAVA_IO_TMPDIR);
    }
}
