package com.hyq.cor;

import com.hyq.tank.GameObject;

/**
 * @author lucky
 * @date 2021/4/8 8:26
 * <p>
 * 碰撞器用来比较两个武器是否碰撞
 */
public interface Collider {

    void collider(GameObject o1, GameObject o2);
}
