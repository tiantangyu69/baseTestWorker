package cc.lee.java.lang;

import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.lang.reflect.Proxy;

/**
 * Created by bjlizhitao on 2017/2/8.
 */
public class TestProxyClass implements TestProxyInterface {
    public void say() {
        System.out.println("aaaaaaaaaaaaaaaaaa");
    }

    public static void main(String[] args) {
        TestProxyInterface proxy = (TestProxyInterface) Proxy.newProxyInstance(TestProxyClass.class.getClassLoader(), new Class[]{TestProxyInterface.class}, new TestInvocationHandler(new TestProxyClass()));
        proxy.say();

        createProxyClassFile();
    }

    public static void createProxyClassFile() {
        String name = "TestProxyClass";
        byte[] data = ProxyGenerator.generateProxyClass(name, new Class[]{TestProxyClass.class});
        try {
            FileOutputStream out = new FileOutputStream(name + ".class");
            out.write(data);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
