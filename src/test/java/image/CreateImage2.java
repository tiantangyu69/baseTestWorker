package image;

import com.google.common.base.Stopwatch;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * Created by bjlizhitao on 2016/7/5.
 */
public class CreateImage2 {
    private static final Random RANDOM = ThreadLocalRandom.current();

    public static final List<String> anonymousNames = new ArrayList<String>();

    public static final List<Color> colors = new ArrayList<Color>();

    private static Stopwatch stopwatch = Stopwatch.createUnstarted();

    static {
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

        colors.add(Color.BLACK);
        colors.add(Color.GREEN);
        colors.add(Color.BLUE);
        colors.add(Color.CYAN);
        colors.add(Color.DARK_GRAY);
        colors.add(Color.GRAY);
        colors.add(Color.LIGHT_GRAY);
        colors.add(Color.MAGENTA);
        colors.add(Color.ORANGE);
        colors.add(Color.PINK);
        colors.add(Color.RED);
        colors.add(Color.YELLOW);

        Color color = new Color(0x35, 0x72, 0xb0);
        colors.add(color);
    }

    public static void main(String[] args) throws Exception {
        stopwatch.start();

        for (int i = 0; i < 2; i++) {
            int randomIndex = RANDOM.nextInt(anonymousNames.size());
            String name = anonymousNames.get(randomIndex).substring(0, 1);// 获取昵称第一个字

            int randomColorIndex = RANDOM.nextInt(colors.size());
            createImage(name, new Font("微软雅黑", Font.TRUETYPE_FONT, 48), new File("e:/a" + i + ".png"), colors.get(randomColorIndex));
        }

        stopwatch.stop();
        System.out.println(stopwatch.elapsed(TimeUnit.MILLISECONDS));
    }

    //根据str,font的样式以及输出文件目录
    public static void createImage(String str, Font font, File outFile, Color bgColor) throws Exception {
        //获取font的样式应用在str上的整个矩形
        Rectangle2D r = font.getStringBounds(str, new FontRenderContext(AffineTransform.getScaleInstance(1, 1), false, false));

        int fontHeight = (int) Math.floor(r.getHeight());//获取单个字符的高度
        // 获取整个str用了font样式的宽度这里用四舍五入后+1保证宽度绝对能容纳这个字符串作为图片的宽度
        int fontWith = (int) Math.round(r.getWidth());

        int width = 100;
        int height = 100;

        // 创建图片
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
        Graphics2D g = image.createGraphics();
        // 消除锯齿
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g.setColor(bgColor);
        g.fillRect(0, 0, width, height);//先用白色填充整张图片,也就是背景
        g.setColor(Color.WHITE);//在换成黑色
        g.setFont(font);//设置画笔字体
        g.drawString(str, (width - fontWith) / 2, fontHeight + 3);//画出字符串
        g.dispose();
        ImageIO.write(image, "png", outFile);//输出png图片
    }
}
