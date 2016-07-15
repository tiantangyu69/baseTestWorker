package lang;

import org.junit.Test;

import java.io.File;

/**
 * Created by bjlizhitao on 2016/7/14.
 */
public class TestLang {
    @Test
    public void test() {
        String premainJarPath = TestLang.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        File jarFile = new File(premainJarPath);
        System.out.println(jarFile.getParentFile().getName());
    }
}
