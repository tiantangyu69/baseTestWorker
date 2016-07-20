package image;

import com.google.common.base.Stopwatch;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by bjlizhitao on 2016/7/19.
 */
public class TestCreateImage {
    @org.junit.Test
    public void testCreateImage() throws IOException {
        Stopwatch stopwatch = Stopwatch.createUnstarted();

        List<String> anonymousNames = getAnonymousNames();

        stopwatch.start();

        for (int i = 0; i < 20; i++) {
            RandomNickNameImage.generateImage(anonymousNames, new RandomNickNameImage.Callback() {
                public void callback(File file) {
                    System.out.println(file.getAbsoluteFile());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        stopwatch.stop();
        System.out.println(stopwatch.elapsed(TimeUnit.MILLISECONDS));
    }

    private List<String> getAnonymousNames() {
        List<String> anonymousNames = new ArrayList<String>();
        anonymousNames.add("牛魔王");
        anonymousNames.add("孙悟空");
        anonymousNames.add("猪八戒");
        anonymousNames.add("唐僧");
        anonymousNames.add("白骨精");
        anonymousNames.add("沙僧");
        anonymousNames.add("太上老君");
        anonymousNames.add("如来");
        anonymousNames.add("观音");
        anonymousNames.add("财神爷");
        anonymousNames.add("玉皇大帝");
        anonymousNames.add("阎王");
        anonymousNames.add("弼马温");
        anonymousNames.add("哪吒");
        anonymousNames.add("二郎神");
        anonymousNames.add("李天王");
        anonymousNames.add("金蝉子");
        anonymousNames.add("蜘蛛精");
        anonymousNames.add("善财童子");
        anonymousNames.add("铁扇公主");

        return anonymousNames;
    }
}
