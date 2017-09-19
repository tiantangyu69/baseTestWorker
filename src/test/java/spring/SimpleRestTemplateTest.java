package spring;

import cc.lee.spring.rest.SimpleRestClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * Created by bjlizhitao on 2016/7/12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-httpclient-rest.xml"})
public class SimpleRestTemplateTest {
    @Resource
    private RestTemplate simpleRestTemplate;

    private RestTemplate template = SimpleRestClient.getClient();

    @Test
    public void testRequestHtml() {
        requestHtml(simpleRestTemplate);
    }

    private void requestHtml(RestTemplate restTemplate) {
        String body = restTemplate.getForObject("http://vip.jr.jd.com", String.class);
        System.out.println(body);
    }

    @Test
    public void testRequestJsonWithCookie() {
        requestJson(simpleRestTemplate);
    }

    private void requestJson(RestTemplate restTemplate) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Cookie", "NTES_SESS=e704arX279FJCQkDw_H_YUP8Fj.2Qm2fkatvKiEa0PvJd3GbdnHABwnzuanZoAkXqmN._y19z9SoUY08b9pGqA.kydL2Y0OoJ3cUAHsk2IUmzHcIPh50oXn8Trf_Uc2Xhmbb356g7BWE7hEQ7ALc_j6jViYB6CMteBnhoi_lUfSY4");

        HttpEntity<String> httpEntity = new HttpEntity<String>(null, httpHeaders);

        ResponseEntity<Count> responseEntity = restTemplate.exchange("http://comment.api.163.com/api/v1/products/{productKey}/follow/followCount",
                HttpMethod.GET, httpEntity, Count.class, "aac69c917e1787ad7bd86cd86afe6efc");

        System.out.println(responseEntity.getBody().getCount());
    }

    @Test
    public void testRestTemplateUtil() {
        requestHtml(template);
    }

    @Test
    public void testRestTemplateUtilToJson() {
        requestJson(template);
    }

    public static class Count {
        private Integer count;

        public Integer getCount() {
            return count;
        }

        public void setCount(Integer count) {
            this.count = count;
        }
    }
}
