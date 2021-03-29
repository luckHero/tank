package com.hyq.tank;

import java.awt.*;

/**
 * @author lucky
 * @date 2021/3/28 17:43
 * 爆炸类
 */
public class Explode {
    public static int WIDTH = ResourceMgr.explodes[0].getWidth();//爆炸的宽度
    public static int HEIGHT = ResourceMgr.explodes[0].getHeight();//爆炸的高度
    private int x, y;
    // private boolean living=true;
    private TankFrame tankFrame;
    private int step = 0;//记录画哪一张图

    public Explode(int x, int y, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.tankFrame = tankFrame;
    }

    //画爆炸的类
    public void paint(Graphics g) {
        g.drawImage(ResourceMgr.explodes[step++], x, y, null);
        if (step >= ResourceMgr.explodes.length) tankFrame.explodes.remove(this);//判断什么时候不画
    }
}
