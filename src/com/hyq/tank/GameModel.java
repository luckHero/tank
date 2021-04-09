package com.hyq.tank;

import com.hyq.cor.BulletTankCollider;
import com.hyq.cor.Collider;
import com.hyq.cor.TankTankCollider;

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

    Collider collider = new BulletTankCollider();//碰撞检测
    Collider tankCollider = new TankTankCollider();
    private List<GameObject> gameObjects = new ArrayList<>();//游戏的容器

    //构造方法,初始化敌方坦克
    public GameModel() {
        int initTankCount = Integer.parseInt(String.valueOf(PropertyMgr.get("initTankCount")));
        //初始化坦克
        for (int i = 0; i < initTankCount; i++) {
            addGameObject(new Tank(60 + i * 100, 200, Dir.DOWN, this, Group.BAD));
        }
    }

    //添加
    public void addGameObject(GameObject go) {
        this.gameObjects.add(go);
    }

    //删除
    public void removeGameObject(GameObject go) {
        this.gameObjects.remove(go);
    }

    public void paint(Graphics g) {
        Color color = g.getColor();
        g.setColor(Color.white);
//        g.drawString("子弹的数量:" + bullets.size(), 10, 60);
//        g.drawString("坦克的数量:" + tanks.size(), 10, 80);
//        g.drawString("爆炸的数量:" + explodes.size(), 10, 100);
        g.setColor(color);
        myTank.paint(g);//绘制坦克
        //TODO 这里有并发修改异常
        //TODO 解决方式一
        //绘画子弹的方法
        for (int i = 0; i < gameObjects.size(); i++) {
            gameObjects.get(i).paint(g);
        }
        //TODO解决方法二  绘画子弹的方法
//        Iterator<Bullet> iterator = bullets.iterator();
//        for (bullets.iterator(); iterator.hasNext(); ) {
//            Bullet bullet = iterator.next();
        //绘画敌人坦克
        for (int i = 0; i < gameObjects.size(); i++) {
            gameObjects.get(i).paint(g);
        }
        //画爆炸
        for (int i = 0; i < gameObjects.size(); i++) {
            gameObjects.get(i).paint(g);
        }
        //互相碰撞
        for (int i = 0; i < gameObjects.size(); i++) {
            for (int j = i + 1; j < gameObjects.size(); j++) {
                GameObject o1 = gameObjects.get(i);
                GameObject o2 = gameObjects.get(j);
                collider.collider(o1, o2);//子弹与坦克的碰撞
                tankCollider.collider(o1, o2);//坦克与坦克的碰撞
            }
        }
        //碰撞检测的方法
//        for (int i = 0; i <(Bullet) gameObjects.size(); i++) {
//            for (int j = 0; j <(Tank) gameObjects.size(); j++) {
//                gameObjects.get(i).collideWith(tanks.get(j));
//            }
//        }

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
