package groovy;

import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DynamicRuleJava {
    private static Map<String, Integer> serverWeightMap = new HashMap<String, Integer>();// 存放服务器

    static {
        serverWeightMap.put("192.168.0.1", 2);
        serverWeightMap.put("192.168.0.2", 1);
        serverWeightMap.put("192.168.0.3", 3);
        serverWeightMap.put("192.168.0.4", 1);
    }

    public static void main(String[] args) throws InstantiationException, IllegalAccessException, IOException {
        File file = new File(DynamicRuleJava.class.getResource("").getPath() + File.separator + "rule.groovy"); // 获取配置文件的路径
        System.out.println(file.exists());
        if (file.exists()) {
            GroovyClassLoader groovyClassLoader = new GroovyClassLoader(Thread
                    .currentThread().getContextClassLoader());// 创建GroovyClassLoader
            Class<?> groovyClass = groovyClassLoader.parseClass(file);// 解析 Groovy Class

            GroovyObject groovyObject = (GroovyObject) groovyClass.newInstance();
            String server = (String) groovyObject.invokeMethod("execute", serverWeightMap);

            groovyClassLoader.close();
            System.out.println(server);
        }
    }
}
