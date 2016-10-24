package cc.lee.spring.dubbo;

/**
 * Created by bjlizhitao on 2016/7/30.
 */
public class HelloServiceImpl implements HelloService {
    public String say(String msg) {
        return "dubbo say: " + msg;
    }
}
