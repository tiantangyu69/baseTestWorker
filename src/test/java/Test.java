import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by bjlizhitao on 2016/10/26.
 */
public class Test {
    @org.junit.Test
    public void test(){
        Pattern pattern = Pattern.compile("^http(s)?://(\\w*\\.)*((163\\.com)|(yeah\\.net)|(youdao\\.com)|(bobo\\.com))(:\\d*)?(/.*)?$");
        Matcher matcher = pattern.matcher("http://sdfsdf.youdao.com");
        System.out.println(matcher.matches());

        System.out.println(111111111);
        System.out.println(0x7fffffff);


        Pattern deviceInfoRegx = Pattern.compile("NewsApp/\\d{1,2}[.\\d{1,2}]* (\\w+)/\\d{1,2}[.\\d{1,2}]*\\s*(NewsAppPro/\\d{1,2}[.\\d{1,2}]*\\s*)?\\u0028([\\w\\s,-._+?/;:'\"<>#$@%]+)\\u0029$");
        Matcher matcher1 = deviceInfoRegx.matcher("NewsApp/4.3.3 Android/5.0.1(honor/g620s-ul00)");
        matcher1.find();
        System.out.println(matcher1.group(1));
        System.out.println(matcher1.group(3));


        Matcher matcher2 = deviceInfoRegx.matcher("NewsApp/17.0 iOS/10.1.1 NewsAppPro/17.0(iPhone 6s Plus)");
        matcher2.find();
        System.out.println(matcher2.group(1));
        System.out.println(matcher2.group(3));

        Pattern testPattern = Pattern.compile("[.,%<>*]+");
        Matcher matcher3 = testPattern.matcher("<.,>");
        System.out.println(matcher3.matches());


    }
}
