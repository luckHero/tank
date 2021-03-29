package com.hyq.tank;

import java.awt.*;
import java.util.Random;

/**
 * @author lucky
 * @date 2021/3/26 14:45
 * 定义坦克类
 */
public class Tank {
    private int x, y;
    public static int WIDTH = ResourceMgr.goodTankU.getWidth(); //坦克的宽度
    public static int HEIGHT = ResourceMgr.goodTankU.getHeight();//坦克
    private Dir dir = Dir.DOWN;//初始坦克方向
    private static final int SPEED = 4;//坦克每次移动的偏移量
    private boolean moving = true;//坦克是否移动
    private boolean living = true;//坦克是否存活
    private TankFrame tankFrame = null;
    private Group group = Group.BAD; //坦克的属性
    private Random random = new Random();

    public Tank(int x, int y, Dir dir, TankFrame tankFrame, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tankFrame = tankFrame;
        this.group = group;
    }

    //绘制坦克自己
    public void paint(Graphics g) {
        if (!living) tankFrame.tanks.remove(this);//坦克是否存活
        switch (dir) {
            case LEFT:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankL : ResourceMgr.badTankL, x, y, null);//判断坦克的属性,画坦克图片
                break;
            case RIGHT:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankR : ResourceMgr.badTankR, x, y, null);//画坦克图片
                break;
            case UP:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankU : ResourceMgr.badTankU, x, y, null);//画坦克图片
                break;
            case DOWN:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankD : ResourceMgr.badTankD, x, y, null);//画坦克图片
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
        if (this.group == Group.BAD && random.nextInt(100) > 95) {//判断坦克的属性,坏坦克随机发射子弹
            this.fire();//让坦克移动的时候随机发射子弹
        }
        if (this.group == Group.BAD && random.nextInt(100) > 85) randomDir(); //随机改变方向
        broundCheck();//碰撞检测
    }

    //碰撞检测的方法
    private void broundCheck() {
        if (this.x < 0) x = 0; //判断坦克是否超出游戏的宽度
        else if (this.y < 30) y = 30;
        else if (this.x > TankFrame.GAME_WIDTH - Tank.WIDTH) x = TankFrame.GAME_WIDTH - Tank.WIDTH;
        else if (this.y > TankFrame.GAME_HEIGHT - Tank.HEIGHT) y = TankFrame.GAME_HEIGHT - Tank.HEIGHT;
    }

    private void randomDir() {
        this.dir = Dir.values()[random.nextInt(4)];//Dir.values()获取dir的所有值 随机取值
    }

    //发射子弹
    public void fire() {
        //1.问题:这里的子弹画不出来 ,解决tank类需要有tankFrame 类的引用
        /**
         *  1.这里通过坦克类对tankFrame 这个类引用
         */
        int bx = this.x + Tank.WIDTH / 2 - Bullet.WIDTH / 2; //调整子弹的宽度
        int by = this.y + Tank.HEIGHT / 2 - Bullet.HEIGH / 2; //调整子弹的高度
        tankFrame.bullets.add(new Bullet(bx, by, this.dir, this.tankFrame, this.group)); //发射子弹
    }

    //坦克死亡的方法
    public void die() {
        this.living = false;
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

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
