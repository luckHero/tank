package com.hyq.factory;

/**
 * @author lucky
 * @date 2021/4/2 8:12
 * <p>
 * 工厂模式
 * 任意定制生产工具 只需要实现一个父类接口
 * 任意定制生产过程 创建不同的XXXFactory
 */
public class CarFactory {

    public Moveable create() {
        System.out.println("a car create ...");
        return new Car();
    }

    public Moveable createPlane() {
        System.out.println("a plane create ...");
        return new Plane();
    }
}
