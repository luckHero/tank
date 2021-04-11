package com.hyq.cor;

import java.util.LinkedList;
import java.util.List;

/**
 * @author lucky
 * @date 2021/4/9 12:38
 * <p>
 * 责任链模式
 */
public class ColldierChain {
    //初始化责任链,添加坦克责任链,子弹责任链
    public ColldierChain(){
        add(new BulletTankCollider());
        add(new TankTankCollider());
    }
    private List<Collider> colliderList = new LinkedList<Collider>();

    /**
     * 添加责任链
     *
     * @param c
     */
    public void add(Collider c) {
        colliderList.add(c);
    }

}
