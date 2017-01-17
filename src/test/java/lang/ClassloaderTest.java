package lang;

import org.junit.Test;
import sun.reflect.Reflection;

import java.security.Security;

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
    public void test1(){
        System.out.println(Reflection.getCallerClass().getClassLoader());
    }
}
