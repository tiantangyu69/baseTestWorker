package guava.base;

import com.google.common.base.Splitter;
import org.junit.Test;

import java.util.List;

/**
 * Created by bjlizhitao on 2017/4/1.
 */
public class SplitterTest {
    @Test
    public void test() {
        // 忽略空字符串
        List<String> list = Splitter.on(",").omitEmptyStrings().splitToList("sfsdf,,f,23f2,f32,3f2,3,f232,f2,3f,2,3f");

        for (String l : list) {
            System.out.println(l);
        }
    }
}
