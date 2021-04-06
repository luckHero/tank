package com.hyq.factory;

/**
 * @author lucky
 * @date 2021/4/1 12:29
 * 工厂设计模式:只要一个方法返回值是一个类就可以称为工厂模式
 * <p>
 * 通过工厂模式创建多个接口的实现
 */
public class Main {
    public static void main(String[] args) {
        Moveable create = new CarFactory().create();
        create.go();
    }
}
