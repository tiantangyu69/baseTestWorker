package cc.lee.spring;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by bjlizhitao on 2017/7/4.
 */
@Service
public class HelloManager {
    @Resource
    private HelloService helloService;

    public void setName(String name) {
        helloService.setName(name);
    }

    public void say() {
        helloService.say();
    }
}
