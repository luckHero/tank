package com.hyq.tank;

import java.awt.*;

/**
 * @author lucky
 * @date 2021/3/26 14:45
 * 定义坦克类
 */
public class Tank {
    private int x, y;
    public static int WIDTH = ResourceMgr.tankD.getWidth(); //坦克的宽度
    public static int HEIGHT = ResourceMgr.tankD.getHeight();//坦克
    private Dir dir = Dir.DOWN;//初始坦克方向
    private static final int SPEED = 5;//坦克每次移动的偏移量
    private boolean moving = false;//坦克是否移动
    private TankFrame tankFrame = null;

    public Tank(int x, int y, Dir dir, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tankFrame = tankFrame;
    }

    //绘制坦克自己
    public void paint(Graphics g) {
        switch (dir) {
            case LEFT:
                g.drawImage(ResourceMgr.tankL, x, y, null);//画坦克图片
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.tankR, x, y, null);//画坦克图片
                break;
            case UP:
                g.drawImage(ResourceMgr.tankU, x, y, null);//画坦克图片
                break;
            case DOWN:
                g.drawImage(ResourceMgr.tankD, x, y, null);//画坦克图片
                break;
        }
        move();//移动的方法
        System.out.println("坦克位置 x:" + x + ",y:" + y);
    }

    //坦克移动的方法
    private void move() {
        if (!moving) return;
        //判断坦克移动方向,向对应方向加减
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

    //发射子弹
    public void fire() {
        //1.问题:这里的子弹画不出来 ,解决tank类需要有tankFrame 类的引用
        /**
         *  1.这里通过坦克类对tankFrame 这个类引用
         */
        int bx = this.x + Tank.WIDTH / 2 - Bullet.WIDTH / 2; //调整子弹的宽度
        int by = this.y + Tank.HEIGHT / 2 - Bullet.HEIGH / 2; //调整子弹的高度
        tankFrame.bullets.add(new Bullet(bx, by, this.dir, this.tankFrame)); //发射子弹
    }


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }
}
