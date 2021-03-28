package com.hyq.tank;

import java.awt.*;

/**
 * @author lucky
 * @date 2021/3/26 18:45
 * 子弹类
 */
public class Bullet {
    private int x, y;
    public static int WIDTH = ResourceMgr.bulletD.getWidth(); //子弹的宽度
    public static int HEIGH = ResourceMgr.bulletD.getHeight();//子弹的高度
    private static final int SPEED = 6;//子弹速度
    boolean live = true;//子弹是否还活着
    private Dir dir;//子弹的方向
    private TankFrame tankFrame = null;

    public Bullet(int x, int y, Dir dir, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        //  System.out.println(tankFrame.HEIGHT);
        this.tankFrame = tankFrame;
    }

    //绘制子弹的方法
    public void paint(Graphics g) {
        if (!live) {
            tankFrame.bullets.remove(this);
        }
        switch (dir) {
            case LEFT:
                g.drawImage(ResourceMgr.bulletL, x, y, null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.bulletR, x, y, null);
                break;
            case UP:
                g.drawImage(ResourceMgr.bulletU, x, y, null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.bulletD, x, y, null);
                break;
            default:
                g.drawImage(ResourceMgr.bulletD, x, y, null);
                break;
        }
//        g.setColor(Color.RED);//设置子弹颜色
//        g.fillOval(x, y, WIDTH, HEIGHT);//绘制一个矩形的内切圆
        move();//移动的方法
    }

    //子弹移动的方法
    private void move() {
        switch (dir) {
            case LEFT:
                x -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case UP:
                y -= SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
        }
        System.out.println("x" + x + ",y:" + y);
        //  System.out.println("TankFrame.GAME_WIDTH:"+TankFrame.GAME_WIDTH +"TankFrame.HEIGHT:"+TankFrame.HEIGHT);
        if (x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT) live = false;//超出范围将子弹属性设为死亡
    }
}
