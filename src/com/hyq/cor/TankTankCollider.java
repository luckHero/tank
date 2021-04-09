package com.hyq.cor;

import com.hyq.tank.GameObject;
import com.hyq.tank.Tank;

/**
 * @author lucky
 * @date 2021/4/8 8:52
 * <p>
 * 坦克与坦克的碰撞
 */
public class TankTankCollider implements Collider {
    @Override
    public void collider(GameObject o1, GameObject o2) {
        if (o1 instanceof Tank && o2 instanceof Tank) {
            Tank t1 = (Tank) o1;
            Tank t2 = (Tank) o2;
            if (t1.rectangle.intersects(t2.rectangle)) {
              t1.stop();
              t2.stop();
            }
        } else {
            return;
        }
    }
}
