package image;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Test {

    public static void main(String[] args) {
        try {

            InputStream imagein = new FileInputStream("E:\\Desert.jpg");
            InputStream imagein2 = new FileInputStream("E:\\Chrysanthemum.jpg");

            BufferedImage image = ImageIO.read(imagein);
            BufferedImage image2 = ImageIO.read(imagein2);
            Graphics g = image.getGraphics();

//                    g.drawImage()

            int width = 100;
            int height = 100;

            int x = (image.getWidth() - width) / 2;
            int y = (image.getHeight() - height) / 2;


            g.drawImage(image2, x, y, width, height, Color.BLACK, null);
            OutputStream outImage = new FileOutputStream("E:\\other.jpg");
            JPEGImageEncoder enc = JPEGCodec.createJPEGEncoder(outImage);
            enc.encode(image);
            imagein.close();
            imagein2.close();
            outImage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @org.junit.Test
    public void testFonts() {
        String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames(Locale.CHINA);
        for (String font : fonts) {
            System.out.println(font);
        }
    }
}