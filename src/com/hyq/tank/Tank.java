package com.hyq.tank;

import com.hyq.strategy.DefaultFireStrategy;
import com.hyq.strategy.FireStrategy;

import java.awt.*;
import java.util.Random;

/**
 * @author lucky
 * @date 2021/3/26 14:45
 * 定义坦克类
 */
public class Tank extends GameObject {
    {
        System.out.println("非静态代码块.......");
    }

    public int x, y;
    public int oldX, oldY;
    public static int WIDTH = ResourceMgr.goodTankU.getWidth(); //坦克的宽度
    public static int HEIGHT = ResourceMgr.goodTankU.getHeight();//坦克
    private Dir dir = Dir.DOWN;//初始坦克方向
    private static final int SPEED = 4;//坦克每次移动的偏移量
    private boolean moving = true;//坦克是否移动
    private boolean living = true;//坦克是否存活
    private Group group = Group.BAD; //坦克的属性
    private Random random = new Random();
    public Rectangle rectangle = new Rectangle();

    public Tank(int x, int y, Dir dir, GameModel gameModel, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.gameModel = gameModel;
        this.group = group;
        rectangle.x = this.x;
        rectangle.y = this.y;
        rectangle.width = WIDTH;
        rectangle.height = HEIGHT;
    }

    public GameModel gameModel;

    //绘制坦克自己
    public void paint(Graphics g) {
        if (!living) gameModel.removeGameObject(this);//坦克是否存活
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

//        System.out.println("坦克位置 x:" + x + ",y:" + y);
    }

    //坦克移动的方法
    private void move() {
        if (!moving) return;
        oldX = x;
        oldY = y;
        if (this.getGroup() == Group.GOOD) {
           // new Thread(() -> new Audio(("audio/tank_move.wav")).play()).start();
        }
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
            DefaultFireStrategy defaultFireStrategy = DefaultFireStrategy.getInstance();
            this.fire(defaultFireStrategy);//让坦克移动的时候随机发射子弹
        }
        if (this.group == Group.BAD && random.nextInt(100) > 90) randomDir(); //随机改变方向
        broundCheck();//游戏边界的碰撞检测
        //碰撞检测完城后更新子弹的矩形
        rectangle.x = this.x;
        rectangle.y = this.y;
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
    public void fire(FireStrategy fireStrategy) {
        fireStrategy.fire(this);
    }

    public void stop() {
        x=oldX;
        y=oldY;

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
