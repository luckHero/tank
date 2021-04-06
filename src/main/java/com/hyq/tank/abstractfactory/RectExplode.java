package com.hyq.tank.abstractfactory;

import com.hyq.tank.Audio;
import com.hyq.tank.ResourceMgr;
import com.hyq.tank.TankFrame;

import java.awt.*;

/**
 * @author lucky
 * @date 2021/4/6 8:50
 * 方形爆炸
 */
public class RectExplode extends BaseExplode {
    public static int WIDTH = ResourceMgr.explodes[0].getWidth();//爆炸的宽度
    public static int HEIGHT = ResourceMgr.explodes[0].getHeight();//爆炸的高度
    private int x, y;
    // private boolean living=true;
    private TankFrame tankFrame;
    private int step = 0;//记录画哪一张图

    public RectExplode(int x, int y, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.tankFrame = tankFrame;
    }

    //画爆炸的类
    public void paint(Graphics g) {
//        g.drawImage(ResourceMgr.explodes[step++], x, y, null);
        Color color = g.getColor();
        g.setColor(Color.RED);
        g.fillRect(x, y, 10 * step, 10 * step);
        step++;
        if (step >= 5) {
            tankFrame.explodes.remove(this);
        }
        g.setColor(color);
//        if (step >= ResourceMgr.explodes.length) tankFrame.explodes.remove(this);//判断什么时候不画
//        new Thread(()->new Audio("audio/explode.wav").play()).start();
    }
}
