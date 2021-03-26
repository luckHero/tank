package com.hyq.tank;

import java.awt.*;

/**
 * @author lucky
 * @date 2021/3/26 18:45
 * 子弹类
 */
public class Bullet {
    private int x, y;
    private static int WIDTH = 30, HEIGHT = 30; //子弹的宽高
    private static final int SPEED = 6;//子弹速度
     boolean live = true;//子弹是否还活着
    private Dir dir;//子弹的方向
    private TankFrame tankFrame=null ;

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
        g.setColor(Color.RED);//设置子弹颜色
        g.fillOval(x, y, WIDTH, HEIGHT);//绘制一个矩形的内切圆
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
        System.out.println("x"+x+",y:"+y);
      //  System.out.println("TankFrame.GAME_WIDTH:"+TankFrame.GAME_WIDTH +"TankFrame.HEIGHT:"+TankFrame.HEIGHT);
        if (x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT) live = false;//超出范围将子弹属性设为死亡
   //     System.out.println("子弹的属性"+live);
    }
}
