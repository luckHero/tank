package com.hyq.abstractfactory.magic;

import com.hyq.abstractfactory.AbstractFactory;
import com.hyq.abstractfactory.Food;
import com.hyq.abstractfactory.Vehicle;
import com.hyq.abstractfactory.Weapon;

/**
 * @author lucky
 * @date 2021/4/4 9:44
 * <p>
 * 魔法工厂类
 */
public class MagicFactory extends AbstractFactory {
    @Override
    public Food createAbstractFood() {
        return new MushRoom();
    }

    @Override
    public Weapon createWeapon() {
        return new MagicStick();
    }

    @Override
    public Vehicle createVehicle() {
        return new Broom();
    }
}
