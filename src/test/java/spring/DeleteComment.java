package spring;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * Created by bjlizhitao on 2017/5/23.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-rest.xml"})
public class DeleteComment {
    @Resource
    private RestTemplate restTemplate;

    @Test
    public void testDeleteComment() throws Exception {
        File file = new File("E:\\gentieids");

        File[] files = file.listFiles();

        int deleteCount = 0;
        for (File yearFile : files) {
            int year = Integer.valueOf(yearFile.getName());

            FileInputStream fileInputStream = new FileInputStream(yearFile);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));

            try {
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    if (StringUtils.isBlank(line))
                        continue;

                    long commentId = Long.parseLong(line.trim());

                    if (sendHttpRequest(year, commentId)) {
                        deleteCount++;
                        System.out.println("success, year:" + year + ";commentId: " + commentId);
                    } else {
                        System.out.println("delete error, year: " + year + ";commentId: " + commentId);
                    }
                }
            } finally {
                fileInputStream.close();
                bufferedReader.close();
            }

            System.out.println(deleteCount);
        }
    }

    private boolean sendHttpRequest(int year, long commentId) {
        try {
            ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://10.164.96.127:8282/api/v1/products/a2869674571f77b5a0867c3d71db5856/year/" + year + "/commentId/" + commentId + "/delete?bimId=hzzhouxin@corp.netease.com&authUser=m18501187960@163.com", null, String.class);

            if (responseEntity.getStatusCode() != HttpStatus.OK) {
                return false;
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
