package com.hyq.abstractfactory.modern;

/**
 * @author lucky
 * @date 2021/4/4 9:23
 * 魔法食物的实现类
 */

import com.hyq.abstractfactory.Food;

/**
 * 面包类
 */
public class Bread extends Food {
    public void printName() {
        System.out.println("面包类.....");
    }
}
