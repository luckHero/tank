package com.hyq.abstractfactory;

import com.hyq.abstractfactory.modern.ModernFactory;

/**
 * @author lucky
 * @date 2021/4/4 9:22
 */
public class Main {
    public static void main(String[] args) {
        //创建现代工厂实现方式
        AbstractFactory modern = new ModernFactory();
        Food food = modern.createAbstractFood();
        Vehicle vehicle = modern.createVehicle();
        Weapon weapon = modern.createWeapon();
        food.printName();
        vehicle.go();
        weapon.shoot();
    }
}
