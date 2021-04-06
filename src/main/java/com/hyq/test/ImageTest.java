package com.hyq.test;

import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author lucky
 * @date 2021/3/27 18:14
 */

/**
 *
 */
public class ImageTest {
    @Test
    public void test() {
        try {
            //将硬盘上的内存图片,读取到内存中.....
            BufferedImage image = ImageIO.read(new File("D:\\bba09abaf3fa8cce0ad5157208b804ef.jpeg"));
            System.out.println(image);
            //通过class类加载器加载classpath 下所有的图片资源
            BufferedImage image1 = ImageIO.read(ImageTest.class.getClassLoader().getResourceAsStream("images/bulletD.gif"));
            System.out.println(image1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
