package com.hyq.tank;

import java.awt.*;

/**
 * @author lucky
 * @date 2021/3/26 18:45
 * 子弹类
 */
public class Bullet {
    private int x,y;
    private static int WIDTH=30,HEIGHT=30; //子弹的宽高
    private static final int SPEED = 1;//子弹速度
    private Dir dir;//子弹的方向
    public Bullet(int x, int y, Dir dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }

    //绘制子弹的方法
    public void paint(Graphics g){
        g.setColor(Color.RED);//设置子弹颜色
        g.fillOval(x,y,WIDTH,HEIGHT);//绘制一个矩形的内切圆
        move();
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
    }
}
