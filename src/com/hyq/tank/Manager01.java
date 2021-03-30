package com.hyq.tank;

/**
 * @author lucky
 * @date 2021/3/30 20:18
 */

/**
 * 饿汉式:
 * 类加载到内存的时候,就实例化一个单列,jvm保证线程安全
 * 唯一缺点:不管用到与否,类装载时就完成实例化
 */
public class Manager01 {
    private static Manager01 Instance = new Manager01();

    private Manager01() { }

    private static Manager01 getInstance() {
        return Instance;
    }

    public static void main(String[] args) {
        Manager01 manager01 = Manager01.getInstance();
        Manager01 manager02 = Manager01.getInstance();
        System.out.println(manager01 == manager02);
    }
}
