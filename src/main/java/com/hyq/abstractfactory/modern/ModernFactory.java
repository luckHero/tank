package com.hyq.abstractfactory.modern;

import com.hyq.abstractfactory.AbstractFactory;
import com.hyq.abstractfactory.Food;
import com.hyq.abstractfactory.Vehicle;
import com.hyq.abstractfactory.Weapon;

/**
 * @author lucky
 * @date 2021/4/4 9:42
 * 现代工厂类
 */
public class ModernFactory extends AbstractFactory {
    @Override
    public Food createAbstractFood() {
        return new Bread();
    }

    @Override
    public Weapon createWeapon() {
        return new Ak47();
    }

    @Override
    public Vehicle createVehicle() {
        return new Car();
    }
}
