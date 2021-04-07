package com.hyq.tank;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lucky
 * @date 2021/4/7 20:03
 * 游戏模型类
 * Facade 模式 调停者模式
 */
public class GameModel {

    Tank myTank = new Tank(200, 500, Dir.UP, this, Group.GOOD);//坦克类
    List<Bullet> bullets = new ArrayList<>(); //设置子弹容器
    //Bullet bullet = new Bullet(300, 300, Dir.DOWN, this); //子弹类
    List<Tank> tanks = new ArrayList<>();//设置敌方坦克的容器
    List<Explode> explodes = new ArrayList<>();//爆炸的集合

    public void paint(Graphics g) {

    }
}
