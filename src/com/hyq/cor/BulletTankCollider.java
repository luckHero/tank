package com.hyq.cor;

import com.hyq.tank.*;

/**
 * @author lucky
 * @date 2021/4/8 8:29
 * 子弹与坦克的碰撞器
 */
//子弹与坦克的碰撞检测
public class BulletTankCollider implements Collider {
    @Override
    public void collider(GameObject o1, GameObject o2) {
        if (o1 instanceof Bullet && o2 instanceof Tank) {
            Bullet bullet = (Bullet) o1;
            Tank tank = (Tank) o2;
            collideWith(tank, bullet);
        } else if (o1 instanceof Tank && o2 instanceof Bullet) {
            collider(o2, o1);
        } else {
            return;
        }
    }


    //子弹和坦克进行碰撞检测
    public void collideWith(Tank tank, Bullet bullet) {
        if (bullet.getGroup() == tank.getGroup()) return; //判断子弹的group 和坦克的group
        //TODO 用一个rect 记录自己的位置 ,这里需要优化 每次子弹都会和坦克进行碰撞
//        Rectangle bulletRect = new Rectangle(this.x, this.y, WIDTH, HEIGH); //子弹的矩形
//        Rectangle tankRect = new Rectangle(tank.getX(), tank.getY(), Tank.WIDTH, Tank.HEIGHT); //坦克的矩形
        if (bullet.rectangle.intersects(tank.rectangle)) { //判断子弹是否与坦克相交
            tank.die();//坦克死亡
            bullet.die();//子弹死亡
            int eX = tank.getX() + Tank.WIDTH / 2 - Explode.WIDTH / 2;
            int eY = tank.getY() + Tank.HEIGHT / 2 - Explode.HEIGHT / 2;
            bullet.gameModel.addGameObject(new Explode(eX, eY, bullet.gameModel));
            new Thread(() -> new Audio("audio/explode.wav").play()).start();
        }
    }
}
