package image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by bjlizhitao on 2016/7/5.
 * 根据昵称列表随机生成该昵称首个字的随机图片，图片背景颜色随机，大小为150px * 150 px，字体大小为 70
 */
public class RandomNickNameImage {
    public enum RandomNickNameImageBgColor {// 生成随机昵称图片的背景颜色
        RGB_1(0xf65343), RGB_2(0x79b94e), RGB_3(0x4189b2), RGB_4(0xecc62c), RGB_5(0xed3f89),
        RGB_6(0x25ba67), RGB_7(0xf99e1c), RGB_8(0x23b99b), RGB_9(0x538be7), RGB_10(0x655cd7);

        RandomNickNameImageBgColor(int rgb) {
            this.rgb = rgb;
        }

        private int rgb;

        public int getRgb() {
            return this.rgb;
        }

        public static List<Color> getColors() {
            List<Color> imageColors = new ArrayList<Color>();
            for (RandomNickNameImageBgColor bgColor : RandomNickNameImageBgColor.values()) {
                imageColors.add(new Color(bgColor.getRgb()));
            }
            return imageColors;
        }
    }


    static {
    }

    // 使用 ThreadLocalRandom 提升多线程并发下的随机数产生性能
    private static final Random RANDOM = ThreadLocalRandom.current();

    private static final Font IMAGE_FONT = new Font("微软雅黑", Font.PLAIN, 70);
    private static final int IMAGE_WIDTH = 150;
    private static final int IMAGE_HEIGHT = 150;

    public static final String TEMP_DIR = System.getProperty("java.io.tmpdir");

    private static final List<Color> IMAGE_COLORS = RandomNickNameImageBgColor.getColors();

    /**
     * 生成图片并获取图片的链接
     *
     * @param anonymousNames
     * @return
     */
    public static void generateImage(List<String> anonymousNames, Callback callback) {
        Color bgColor = getRandomColor();
        String anonymousNamePrefix = getRandomAnonymousNamePrefix(anonymousNames);

        File imageFile = doGenerateImage(anonymousNamePrefix, bgColor);
        callback.callback(imageFile);
        deleteTempFile(imageFile);
    }

    private static void deleteTempFile(File imageFile) {
        if (null != imageFile) {
            try {
                Files.deleteIfExists(Paths.get(imageFile.toURI()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static Color getRandomColor() {
        int randomColorIndex = RANDOM.nextInt(IMAGE_COLORS.size());
        return IMAGE_COLORS.get(randomColorIndex);
    }

    private static String getRandomAnonymousNamePrefix(List<String> anonymousNames) {
        int randomIndex = RANDOM.nextInt(anonymousNames.size());
        return anonymousNames.get(randomIndex).substring(0, 1);// 获取昵称第一个字
    }

    private static File doGenerateImage(String anonymousNamePrefix, Color bgColor) {
        try {
            return createImage(anonymousNamePrefix, bgColor);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //根据str,font的样式以及输出文件目录
    private static File createImage(String str, Color bgColor) throws Exception {
        // 创建图片
        BufferedImage image = new BufferedImage(IMAGE_WIDTH, IMAGE_HEIGHT, BufferedImage.TYPE_INT_BGR);

        Graphics2D imageGraphics = image.createGraphics();
        imageGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);// 消除锯齿
        imageGraphics.setColor(bgColor);// 设置背景色
        imageGraphics.fillRect(0, 0, IMAGE_WIDTH, IMAGE_HEIGHT);//填充背景

        imageGraphics.setColor(Color.WHITE);// 设置画笔颜色为白色，即字体颜色为白色
        imageGraphics.setFont(IMAGE_FONT);//设置画笔字体

        CharInfo charInfo = getCharInfo(str);// 获取画的字符的实际宽度和长度
        imageGraphics.drawString(str, (IMAGE_WIDTH - charInfo.getWidth()) / 2, charInfo.getHeight() + 6);// 计算字符串在图中居中的位置并画出字符串
        imageGraphics.dispose();

        File imageFile = new File(getImagePath(bgColor, str));
        ImageIO.write(image, "png", imageFile);

        return imageFile;
    }

    /**
     * 获取图片临时存放路径
     *
     * @param color
     * @param str
     * @return
     */
    private static String getImagePath(Color color, String str) {
        return TEMP_DIR + File.separator + color.getRGB() + str + ".png";
    }

    /**
     * 获取字符占用图片的实际长度和宽度
     *
     * @param str
     * @return
     */
    private static CharInfo getCharInfo(String str) {
        //获取font的样式应用在str上的整个矩形
        Rectangle2D rectangle2D = IMAGE_FONT.getStringBounds(str, new FontRenderContext(AffineTransform.getScaleInstance(1, 1), false, false));

        int fontHeight = (int) Math.floor(rectangle2D.getHeight());//获取单个字符的高度
        // 获取整个str用了font样式的宽度这里用四舍五入后+1保证宽度绝对能容纳这个字符串作为图片的宽度
        int fontWith = (int) Math.round(rectangle2D.getWidth());

        return new CharInfo(fontWith, fontHeight);
    }

    private static class CharInfo {
        private int width;
        private int height;

        public CharInfo(int width, int height) {
            this.width = width;
            this.height = height;
        }

        public int getWidth() {
            return this.width;
        }

        public int getHeight() {
            return this.height;
        }

    }

    public interface Callback {
        void callback(File file);
    }
}
