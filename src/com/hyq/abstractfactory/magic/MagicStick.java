package com.hyq.abstractfactory.magic;

import com.hyq.abstractfactory.Weapon;

/**
 * @author lucky
 * @date 2021/4/4 9:26
 * 魔法棒
 */
public class MagicStick extends Weapon {
    //开火
    public void shoot() {
        System.out.println("dian dian  dian ....");
    }
}
