package com.hyq.tank;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lucky
 * @date 2021/4/7 20:03
 * 游戏模型类
 * Facade 模式 门面模式
 * Model
 */
public class GameModel {
    Tank myTank = new Tank(200, 500, Dir.UP, this, Group.GOOD);//坦克类
    List<Bullet> bullets = new ArrayList<>(); //设置子弹容器
    //Bullet bullet = new Bullet(300, 300, Dir.DOWN, this); //子弹类
    List<Tank> tanks = new ArrayList<>();//设置敌方坦克的容器
    List<Explode> explodes = new ArrayList<>();//爆炸的集合
    //构造方法,初始化敌方坦克
    public GameModel() {
        int initTankCount = Integer.parseInt(String.valueOf(PropertyMgr.get("initTankCount")));
        //初始化坦克
        for (int i = 0; i < initTankCount; i++) {
            tanks.add(new Tank(60 + i * 100, 200, Dir.DOWN, this, Group.BAD));
        }
    }

    public void paint(Graphics g) {
        Color color = g.getColor();
        g.setColor(Color.white);
        g.drawString("子弹的数量:" + bullets.size(), 10, 60);
        g.drawString("坦克的数量:" + tanks.size(), 10, 80);
        g.drawString("爆炸的数量:" + explodes.size(), 10, 100);
        g.setColor(color);
        myTank.paint(g);//绘制坦克
        //TODO 这里有并发修改异常
//        bullets.forEach(b -> {//画子弹
//            b.paint(g);
//        });
        //TODO 解决方式一
        //绘画子弹的方法
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).paint(g);
        }
        //TODO解决方法二  绘画子弹的方法
//        Iterator<Bullet> iterator = bullets.iterator();
//        for (bullets.iterator(); iterator.hasNext(); ) {
//            Bullet bullet = iterator.next();
        //绘画敌人坦克
        for (int i = 0; i < tanks.size(); i++) {
            tanks.get(i).paint(g);
        }
        //画爆炸
        for (int i = 0; i < explodes.size(); i++) {
            explodes.get(i).paint(g);
        }
        //碰撞检测的方法
        for (int i = 0; i < bullets.size(); i++) {
            for (int j = 0; j < tanks.size(); j++) {
                bullets.get(i).collideWith(tanks.get(j));
            }
        }

//            /**
//             *  成员变量被private修饰,在其他类不能不引用,那这个成员变量的在内存中是放在哪里的
//             *  为什么通过反射能够拿到类的私有成员变量
//             */
//            if (!bullet.living) iterator.remove();
//        }
    }

    public Tank getMyTank() {
        return myTank;
    }
}
