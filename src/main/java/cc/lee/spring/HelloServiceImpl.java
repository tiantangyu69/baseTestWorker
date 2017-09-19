package cc.lee.spring;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * Created by bjlizhitao on 2016/7/11.
 */
@Service
@Scope("prototype")
public class HelloServiceImpl implements HelloService {
    private String name;

    public void setName(String name){
        this.name = name;
    }

    public void say() {
        System.out.println("aaaaaaaaaaaaaaaaaaaa: " + this.name);
    }
}
