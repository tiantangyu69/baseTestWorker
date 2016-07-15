/*
package image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CreateImage {
    private static final List<String> anonymousNames = new CopyOnWriteArrayList<String>();

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
    }

    public static void main(String[] args) throws Exception {
        int width = 100; // 图片宽度
        int height = 100;// 图片长度
        Collections.shuffle(anonymousNames);// 随机打乱昵称顺序

        String name = anonymousNames.get(0).substring(0, 1);// 获取昵称第一个字

        File file = new File("e:/image.jpg");//  生成的图片地址

        Font font = new Font("微软雅黑", Font.BOLD, 72);
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = (Graphics2D) bi.getGraphics();
        g2.setBackground(Color.GREEN);
        g2.clearRect(0, 0, width, height);
        g2.setPaint(Color.WHITE);

        FontRenderContext context = g2.getFontRenderContext();
        Rectangle2D bounds = font.getStringBounds(name, context);
        double x = (width - bounds.getWidth()) / 2;
        double y = (height - bounds.getHeight()) / 2;
        double ascent = -bounds.getY();
        double baseY = y + ascent;

        g2.drawString(name, (int) x, (int) baseY);

        ImageIO.write(bi, "jpg", file);
    }
}    */
