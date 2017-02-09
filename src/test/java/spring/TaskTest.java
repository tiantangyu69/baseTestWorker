package spring;

import cc.lee.spring.retry.Service;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by bjlizhitao on 2016/7/13.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-task.xml"})
public class TaskTest {
    @Resource
    private Service service;

    @Test
    public void test(){
        service.service();
    }

    @Test
    public void testTask() throws InterruptedException {
        Thread.sleep(10000);
    }
}
