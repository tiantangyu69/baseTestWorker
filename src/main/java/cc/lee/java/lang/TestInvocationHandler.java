package cc.lee.java.lang;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by bjlizhitao on 2017/2/8.
 */
public class TestInvocationHandler implements InvocationHandler {
    private Object targetObject;

    public TestInvocationHandler(Object targetObject) {
        this.targetObject = targetObject;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("proxy class");
        return method.invoke(targetObject, args);
    }
}
