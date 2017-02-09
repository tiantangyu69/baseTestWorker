package cc.lee.java.lang;

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
    }
}
