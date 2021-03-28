package com.hyq.tank;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author lucky
 * @date 2021/3/27 18:39
 */
//加载坦克图片资源的类
public class ResourceMgr {
    public static BufferedImage tankL, tankR, tankU, tankD; //坦克图片
    public static BufferedImage bulletL, bulletR, bulletU, bulletD;//子弹图片
    public static  BufferedImage[] explodes=new BufferedImage[16];//爆炸的图片
    static {
        try {
            tankL = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/tankL.gif"));
            tankR = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/tankR.gif"));
            tankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/tankU.gif"));
            tankD = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/tankD.gif"));
            //加载子弹资源
            bulletL = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletL.gif"));
            bulletR = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletR.gif"));
            bulletU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletU.gif"));
            bulletD = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletD.gif"));
            for (int i = 0; i < 16; i++) {
                explodes[i]=ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/e"+(i+1)+".gif"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
