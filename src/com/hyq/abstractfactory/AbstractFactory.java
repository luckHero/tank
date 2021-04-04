package com.hyq.abstractfactory;

/**
 * @author lucky
 * @date 2021/4/4 9:32
 */
public abstract class AbstractFactory {
    public abstract Food createAbstractFood();

    public abstract Weapon createWeapon();

    public abstract Vehicle createVehicle();
}
