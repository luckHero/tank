package com.hyq.singleton;

/**
 * @author lucky
 * @date 2021/3/31 8:19
 * 通过枚举来解决线程安全问题,还可以解决反序列化的问题
 * 序列化:讲一个对象转换成二进制数组
 * 反序列化:将一个二进制的数组转换成对对象
 * 前面的几种方式可以通过反射来破化
 * 枚举之所以能够反序列化是因为枚举没有构造方法
 */
public enum Manager04 {
    INSTANCE;

    public void m() {
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> System.out.println(Manager04.INSTANCE.hashCode())).start();
        }
    }
}
