package lang;

import com.google.common.primitives.Longs;
import org.junit.Test;
import sun.reflect.Reflection;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.security.Security;
import java.sql.Timestamp;

/**
 * Created by bjlizhitao on 2017/1/9.
 */
public class ClassloaderTest {
    @Test
    public void test() {
        ClassLoader systemClassloader = ClassLoader.getSystemClassLoader();
        System.out.println(systemClassloader);
        System.out.println("parent classloader:" + systemClassloader.getParent());
        System.out.println("parent parent classloader:" + systemClassloader.getParent().getParent());
        System.out.println(ClassloaderTest.class.getClassLoader());
        System.out.println(this.getClass().getClassLoader());
        System.out.println(Thread.currentThread().getContextClassLoader());
        System.out.println(System.getProperty("java.class.path"));
        System.out.println(System.getProperty("user.dir"));


        if (System.getSecurityManager() != null) {
            String definition = Security.getProperty("package.definition");
            if (definition != null && definition.length() > 0) {
                System.out.println(definition);
            }
        }
        String access = Security.getProperty("package.access");
        System.out.println(access);
    }

    @Test
    public void test1() throws IOException {
        System.out.println(Reflection.getCallerClass().getClassLoader());
        System.out.println(System.getProperty("java.ext.dirs"));
        System.out.println(System.getProperty("user.dir"));
        System.out.println((new File(System.getProperty("user.dir"), "..")).getCanonicalPath());

        File file = new File("D:\\apache-tomcat-7.0.69\\lib");
//        System.out.println(file.toURI());

        URL url = new URL("file:/D:/apache-tomcat-7.0.69/lib");
        System.out.println(url);
    }

    @Test
    public void test2(){
        /*Timestamp timestamp = new Timestamp(1498632150222L);

        System.out.println(timestamp.getTime());
        System.out.println(1498632150222L & 0x15CED6FDBF0L);
                                        11100110001*/
       /* 10101110011101101011011111101110011001110
          10101110011101101011011111101101111110000

                                       001111100111

        11011110*/


    }
}
