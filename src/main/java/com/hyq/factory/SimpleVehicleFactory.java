package com.hyq.factory;

/**
 * @author lucky
 * @date 2021/4/1 12:34
 * 简单工厂可扩展性不好
 */
public class SimpleVehicleFactory {
    /**
     * 想创建什么对象只要在工厂里添加对应的方法
     * @return
     */
    public Car createCar() {
        //before processing
        return new Car();
    }

    public Plane createPlane() {
        //before Processing       前置操作
        return new Plane();
    }
}
