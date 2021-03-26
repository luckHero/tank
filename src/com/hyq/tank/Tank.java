package com.hyq.tank;

import java.awt.*;

/**
 * @author lucky
 * @date 2021/3/26 14:45
 * 定义坦克类
 */
public class Tank {
    private int x, y;
    private Dir dir = Dir.DOWN;
    private static final int SPEED=10;

    public Tank(int x, int y, Dir dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public Tank() {
    }
    //绘制坦克自己
    public void paint(Graphics g) {
        g.fillRect(x, y, 60, 60);//改变方块的大小
        //判断键盘按下的位置,
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
        System.out.println("坦克位置 x:" + x + ",y:" + y);
    }
}
