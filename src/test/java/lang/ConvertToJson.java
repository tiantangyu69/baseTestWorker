package lang;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by bjlizhitao on 2017/5/22.
 */
public class ConvertToJson {
    public static void main(String[] args) throws Exception {
        String filePath;
        if (args.length != 0) {
            filePath = args[0];
        } else {
            filePath = "D:\\ipList.txt";
        }

        FileInputStream fileInputStream = new FileInputStream(new File(filePath));

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));

        List<String> ipList = Lists.newArrayList();

        String line;
        int index = 1;
        while (null != (line = bufferedReader.readLine())) {
            String ip = line;
            ipList.add(ip.trim());

            if (index % 200 == 0) {
                System.out.println(index);
                System.out.println(JSON.toJSONString(ipList));
                ipList.clear();
            }


            index++;
        }
        System.out.println("===");
        System.out.println(JSON.toJSONString(ipList));

        fileInputStream.close();
        bufferedReader.close();

    }
}
