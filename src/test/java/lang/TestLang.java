package lang;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.time.FastDateFormat;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.helpers.Util;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.sql.Timestamp;
import java.util.*;
import java.util.concurrent.ConcurrentMap;
import java.util.regex.Pattern;

/**
 * Created by bjlizhitao on 2016/7/14.
 */
public class TestLang {
    static final String CODES_PREFIX = "http://www.slf4j.org/codes.html";
    private static String STATIC_LOGGER_BINDER_PATH = "org/slf4j/impl/StaticLoggerBinder.class";
    static final String MULTIPLE_BINDINGS_URL = CODES_PREFIX + "#multiple_bindings";
    private static Logger logger = LoggerFactory.getLogger(TestLang.class);

    @Test
    public void testTime() {
        System.out.println(FastDateFormat.getInstance("HH").format(new Timestamp(System.currentTimeMillis())));


        Calendar todayStart = Calendar.getInstance();
        todayStart.add(Calendar.DATE, -1);
        todayStart.set(Calendar.HOUR_OF_DAY, 0);
        todayStart.set(Calendar.MINUTE, 0);
        todayStart.set(Calendar.SECOND, 0);
        todayStart.set(Calendar.MILLISECOND, 0);

        Timestamp now = new Timestamp(todayStart.getTime().getTime());
        System.out.println(FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss").format(now));
    }

    @Test
    public void test() {
        String premainJarPath = TestLang.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        File jarFile = new File(premainJarPath);
        System.out.println(jarFile.getParentFile().getName());
    }

    @Test
    public void testLog() {
//        singleImplementationSanityCheck();
        logger.info("aaaa");
    }


    private static void singleImplementationSanityCheck() {
        try {
            ClassLoader loggerFactoryClassLoader = TestLang.class
                    .getClassLoader();
            Enumeration paths;
            if (loggerFactoryClassLoader == null) {
                paths = ClassLoader.getSystemResources(STATIC_LOGGER_BINDER_PATH);
            } else {
                paths = loggerFactoryClassLoader
                        .getResources(STATIC_LOGGER_BINDER_PATH);
            }
            // use Set instead of list in order to deal with  bug #138
            // LinkedHashSet appropriate here because it preserves insertion order during iteration
            Set implementationSet = new LinkedHashSet();
            while (paths.hasMoreElements()) {
                URL path = (URL) paths.nextElement();
                implementationSet.add(path);
            }
            if (implementationSet.size() > 1) {
                Util.report("Class path contains multiple SLF4J bindings.");
                Iterator iterator = implementationSet.iterator();
                while (iterator.hasNext()) {
                    URL path = (URL) iterator.next();
                    Util.report("Found binding in [" + path + "]");
                }
                Util.report("See " + MULTIPLE_BINDINGS_URL + " for an explanation.");
            }
        } catch (IOException ioe) {
            Util.report("Error getting resources from path", ioe);
        }
    }

    @Test
    public void testSubList() {
        List<String> lists = Lists.newArrayList();

        lists.add("aaa");
        lists.add("bbb");
        lists.add("ccc");
        lists.add("ddd");
        lists.add("eee");
        lists.add("fff");
        lists.add("ggg");
        lists.add("hhh");
        lists.add("iii");
        lists.add("jjj");
        lists.add("kkk");
        lists.add("lll");
        lists.add("mmm");
        lists.add("ddd");

        System.out.println(lists.subList(0, 10));
        System.out.println(lists.subList(lists.size() - 2, lists.size()));

    }

    @Test
    public void testAAA() throws Exception {
        String s = URLDecoder.decode("alphago+%e5%b0%b1%e6%98%af%e5%8e%89%e5%ae%b3", "UTF-8");

        String gbk = new String(s.getBytes("UTF-8"), "GBK");
        System.out.println(s);
        System.out.println(gbk);
        String utf8 = new String(gbk.getBytes("GBK"), "UTF-8");
        System.out.println(utf8);
    }

    @Test
    public void testPutIfAbsent(){
        ConcurrentMap<String, String> map = Maps.newConcurrentMap();
        System.out.println(map.putIfAbsent("aaa", "1"));
        System.out.println(map.putIfAbsent("aaa", "2"));
    }

    @Test
    public void testNginxConf(){

        Pattern pattern1 = Pattern.compile("^/api/v1/products/(.*)/users/realname/(smscaptcha|bind)");
        Pattern pattern2 = Pattern.compile("^/api/v1/products/(.*)/users/bindPhStatus");

        System.out.println(pattern1.matcher("/api/v1/products/sdfsdfsdfsdfsdfsdfsdf/users/realname/smscaptcha?phone=aaa&sdfsdf=?").matches());
        System.out.println(pattern1.matcher("/api/v1/products/sdfsdfsdfsdfsdfsdfsdf/app/users/realname/smscaptcha?phone=18888888888&test=test").matches());
        System.out.println(pattern1.matcher("/api/v1/products/sdfsdfsdfsdfsdfsdfsdf/users/realname/bind?phone=aaa&sdfsdf=?").matches());
        System.out.println(pattern1.matcher("/api/v1/products/sdfsdfsdfsdfsdfsdfsdf/app/users/realname/bind?phone=aaa&sdfsdf=?").matches());
        System.out.println(pattern2.matcher("/api/v1/products/sdfsdfsdfsdfsdfsdfsdf/users/bindPhStatus?phone=aaa&sdfsdf=?").matches());
        System.out.println(pattern2.matcher("/api/v1/products/sdfsdfsdfsdfsdfsdfsdf/app/users/bindPhStatus?phone=aaa&sdfsdf=?").matches());
    }
}
