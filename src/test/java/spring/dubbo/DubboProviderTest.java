package spring.dubbo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

/**
 * Created by bjlizhitao on 2016/7/30.
 */
@ContextConfiguration(locations = {"classpath:dubbo-service.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class DubboProviderTest {
    @Test
    public void testDubboService() throws IOException {
        System.out.println("dubbo 启动成功");
        System.in.read();
    }
}
