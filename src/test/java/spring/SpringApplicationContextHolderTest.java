package spring;

import cc.lee.spring.ApplicationContextHolder;
import cc.lee.spring.HelloService;
import cc.lee.spring.HelloServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by bjlizhitao on 2016/7/11.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-config.xml"})
public class SpringApplicationContextHolderTest {
    @Test
    public void test() {
        HelloService helloService = ApplicationContextHolder.getBean(HelloServiceImpl.class);
        helloService.say();
    }
}
