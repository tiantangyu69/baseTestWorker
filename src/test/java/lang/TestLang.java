package lang;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.time.FastDateFormat;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.helpers.Util;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.*;

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
}
