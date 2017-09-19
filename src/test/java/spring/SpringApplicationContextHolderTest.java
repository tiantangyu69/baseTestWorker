package spring;

import cc.lee.spring.HelloService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by bjlizhitao on 2016/7/11.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-config.xml"})
public class SpringApplicationContextHolderTest {
    @Resource
    private HelloService helloService;

    @Test
    public void test() {
//        HelloManager helloService = ApplicationContextHolder.getBean(HelloManager.class);
        helloService.setName("service1");
        helloService.say();

//        HelloService helloService2 = ApplicationContextHolder.getBean(HelloServiceImpl.class);
//        helloService2.say();
    }

    @Test
    public void test2() {
//        HelloManager helloService = ApplicationContextHolder.getBean(HelloManager.class);
        helloService.say();
    }
}
