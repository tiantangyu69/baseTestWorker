package image;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import javax.swing.*;
import java.awt.*;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;

/**
 * Created by bjlizhitao on 2016/7/5.
 */
public class Test2 {
    /**
     * @param souchFilePath    ：源图片路径
     * @param targetFilePath   ：生成后的目标图片路径
     * @param markContent      :要加的文字
     * @param markContentColor :文字颜色
     * @param qualNum          :质量数字
     * @param fontType         :字体类型
     * @param fontSize         :字体大小
     * @return
     */
    public static void createMark(String souchFilePath, String targetFilePath,
                                  String markContent, Color markContentColor, float qualNum,
                                  String fontType, int fontSize, int w, int h, Color color) {
        markContentColor = color;
                          /* 构建要处理的源图片 */
        ImageIcon imageIcon = new ImageIcon(souchFilePath);
                          /* 获取要处理的图片 */
        Image image = imageIcon.getImage();
        // Image可以获得图片的属性信息
        int width = image.getWidth(null);
        int height = image.getHeight(null);
        // 为画出与源图片的相同大小的图片（可以自己定义）
        BufferedImage bImage = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        // 构建2D画笔
        Graphics2D g = bImage.createGraphics();
                          /* 设置2D画笔的画出的文字颜色 */
        g.setColor(markContentColor);
                          /* 设置2D画笔的画出的文字背景色 */
        g.setBackground(Color.white);
                          /* 画出图片 */
        g.drawImage(image, 0, 0, null);
                          /* --------对要显示的文字进行处理-------------- */
        AttributedString ats = new AttributedString(markContent);
        Font font = new Font(fontType, Font.BOLD, fontSize);
        g.setFont(font);

        /* 消除java.awt.Font字体的锯齿 */
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        /* 消除java.awt.Font字体的锯齿 */

        //更多实例请访问 http://www.walkerjava.com/forum.php?mod=viewthread&tid=135
        // font = g.getFont().deriveFont(30.0f);
        ats.addAttribute(TextAttribute.FONT, font, 0, markContent.length());
        AttributedCharacterIterator iter = ats.getIterator();

        /* 添加水印的文字和设置水印文字出现的内容 ----位置 */
        g.drawString(iter, width - w, height - h);
        /* --------对要显示的文字进行处理-------------- www.it165.net */
        g.dispose();// 画笔结束
        try {
            // 输出 文件 到指定的路径
            FileOutputStream out = new FileOutputStream(targetFilePath);
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(bImage);
            param.setQuality(qualNum, true);
            encoder.encode(bImage, param);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {//方正楷体简体
        Test2.createMark("E:\\Desert.jpg", "E:\\Desert2.jpg", "这是用java程序给图片添加的文字水印", null, 1, "微软雅黑", 48, 700, 300, Color.GRAY);
    }
}
