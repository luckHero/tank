package com.hyq.tank;

import java.awt.*;

/**
 * @author lucky
 * @date 2021/3/26 14:45
 * 定义坦克类
 */
public class Tank {
    private int x, y;//坦克的x,y
    private Dir dir = Dir.DOWN;//初始坦克方向
    private static final int SPEED = 5;//坦克每次移动的偏移量
    private boolean moving = false;//坦克是否移动

    public Tank(int x, int y, Dir dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }

    public Tank() {
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

    //绘制坦克自己
    public void paint(Graphics g) {
        g.fillRect(x, y, 60, 60);//改变方块的大小
        move();//移动的方法
        System.out.println("坦克位置 x:" + x + ",y:" + y);
    }
    //坦克移动的方法
    private void move() {
        if(!moving)return;
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
}
