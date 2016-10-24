package lang;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.helpers.Util;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by bjlizhitao on 2016/7/14.
 */
public class TestLang {
    static final String CODES_PREFIX = "http://www.slf4j.org/codes.html";
    private static String STATIC_LOGGER_BINDER_PATH = "org/slf4j/impl/StaticLoggerBinder.class";
    static final String MULTIPLE_BINDINGS_URL = CODES_PREFIX+"#multiple_bindings";
    private static Logger logger = LoggerFactory.getLogger(TestLang.class);

    @Test
    public void test() {
        String premainJarPath = TestLang.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        File jarFile = new File(premainJarPath);
        System.out.println(jarFile.getParentFile().getName());
    }

    @Test
    public void testLog(){
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
                while(iterator.hasNext()) {
                    URL path = (URL) iterator.next();
                    Util.report("Found binding in [" + path + "]");
                }
                Util.report("See " + MULTIPLE_BINDINGS_URL + " for an explanation.");
            }
        } catch (IOException ioe) {
            Util.report("Error getting resources from path", ioe);
        }
    }
}
