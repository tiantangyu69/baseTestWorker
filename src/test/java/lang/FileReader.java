/*
package lang;

import com.google.common.base.Strings;
import com.google.common.collect.Maps;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

*/
/**
 * Created by bjlizhitao on 2017/5/17.
 *//*

public class FileReader {
    public static class StasticsData implements Comparable<StasticsData> {
        private String key;
        private AtomicLong count;
        private AtomicLong totalTimes;
        private Long maxTime;

        public StasticsData(String key, AtomicLong count, AtomicLong totalTimes, Long maxTime) {
            this.key = key;
            this.count = count;
            this.totalTimes = totalTimes;
            this.maxTime = maxTime;
        }

        public AtomicLong getCount() {
            return count;
        }

        public AtomicLong getTotalTimes() {
            return totalTimes;
        }

        public Float getAverageTime() {
            return totalTimes.floatValue() / count.floatValue();
        }

        public Long getMaxTime() {
            return maxTime;
        }

        public void setMaxTime(Long maxTime) {
            this.maxTime = maxTime;
        }

        public String getKey() {
            return key;
        }

        @Override
        public int compareTo(StasticsData o) {
            return o.getAverageTime().compareTo(this.getAverageTime());
        }
    }

    public static void main(String[] args) throws Exception {
        String filePath;
        if (args.length != 0) {
            filePath = args[0];
        } else {
            filePath = "D:\\add_method_runtime_statistics.log";
        }

        FileInputStream fileInputStream = new FileInputStream(new File(filePath));

        Map<String, StasticsData> interfaceMap = Maps.newHashMap();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));

        String line;
        while (null != (line = bufferedReader.readLine())) {
            String[] lines = line.split(",");
            String key = lines[0].trim();
            Long time = Long.valueOf(lines[1].trim());

            if (interfaceMap.containsKey(key)) {
                StasticsData stasticsData = interfaceMap.get(key);
                stasticsData.getCount().incrementAndGet();
                stasticsData.getTotalTimes().addAndGet(time);
                if (stasticsData.getMaxTime() < time) {
                    stasticsData.setMaxTime(time);
                }
            } else {
                interfaceMap.put(key, new StasticsData(key, new AtomicLong(1), new AtomicLong(time), time));
            }
        }
        List<StasticsData> collections = new ArrayList<StasticsData>(interfaceMap.values());
        Collections.sort(collections);

        for (StasticsData stasticsData : collections) {
            System.out.println(Strings.padEnd("慢查询次数：" + stasticsData.getCount(), 20, ' ')
                    + Strings.padEnd("监控key：" + stasticsData.getKey(), 100, ' ')
                    + Strings.padEnd("平均 rt：" + stasticsData.getAverageTime().intValue() + " ms", 30, ' ')
                    + "max rt: " + stasticsData.getMaxTime() + " ms");
        }
    }
}
*/
