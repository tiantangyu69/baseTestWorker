package spring.dubbo;

import cc.lee.spring.HelloService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * Created by bjlizhitao on 2016/7/30.
 */
@ContextConfiguration(locations = {"classpath:dubbo-consumer.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class DubboConsumerTest {
    @Resource
    private HelloService helloService;

    @Test
    public void testDubboService() throws IOException {
        helloService.say();
    }
}
