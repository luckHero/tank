package com.hyq.cor;

import com.hyq.tank.GameObject;
import org.omg.CORBA.TRANSACTION_MODE;

import java.util.LinkedList;
import java.util.List;

/**
 * @author lucky
 * @date 2021/4/9 12:38
 * <p>
 * 责任链模式
 * <p>
 * 让链条内部实现责任链模式
 */
public class ColldierChain implements Collider {
    private List<Collider> colliderList = new LinkedList<Collider>();

    //初始化责任链,添加坦克责任链,子弹责任链
    public ColldierChain() {
        add(new BulletTankCollider());
        add(new TankTankCollider());
    }


    /**
     * 添加责任链
     *
     * @param c
     */
    public void add(Collider c) {
        colliderList.add(c);
    }

    public boolean collider(GameObject o1, GameObject o2) {
        for (int i = 0; i < colliderList.size(); i++) {
            if (!colliderList.get(i).collider(o1, o2)) {
                return false;
            }
        }
        return true;
    }
}
