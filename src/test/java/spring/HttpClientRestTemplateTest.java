package spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * Created by bjlizhitao on 2016/7/12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-httpclient-rest.xml"})
public class HttpClientRestTemplateTest {
    @Resource
    private RestTemplate restTemplate;

    @Test
    public void testRequestHtml() {
        String body = restTemplate.getForObject("http://vip.jr.jd.com", String.class);
        System.out.println(body);
    }

    @Test
    public void testRequestJsonWithCookie() {
        SimpleRestTemplateTest.Count count = restTemplate.getForObject("http://comment.api.163.com/api/v1/products/{productKey}/follow/followCount",
                SimpleRestTemplateTest.Count.class, "aac69c917e1787ad7bd86cd86afe6efc");
        System.out.println(count.getCount());
    }
}
