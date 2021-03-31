package com.hyq.tank;

/**
 * @author lucky
 * @date 2021/3/31 8:07
 * 静态内部类方式
 * JVM保证单列
 * 加载外部类的时不会加载内部类,可以保证实现单利
 */
public class Manager03 {
    private Manager03() {
    }

    //静态内部类
    private static class Manager03Holder {
        private final static Manager03 Instance = new Manager03();
    }

    //通过03的持有者调用它的静态方法是先Manager03 的初始化
    public static Manager03 getInstance() {
        return Manager03Holder.Instance;
    }

    public void m() {
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> System.out.println(Manager03.getInstance().hashCode())).start();
        }
    }
}
