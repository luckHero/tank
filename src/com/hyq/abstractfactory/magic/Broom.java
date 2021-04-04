package com.hyq.abstractfactory.magic;

import com.hyq.abstractfactory.Vehicle;

/**
 * @author lucky
 * @date 2021/4/4 9:28
 * 扫帚魔法的实现类
 */
public class Broom extends Vehicle {
    public void go() {
        System.out.println("扫帚  go go go...");
    }
}
