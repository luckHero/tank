package com.hyq.tank;

/**
 * @author lucky
 * @date 2021/3/30 21:01
 */

/**
 * 懒汉式的单列模式,存在线程安全问题
 */
public class Manager02 {
    private static Manager02 Instance = null;

    private Manager02() {
    }

    public static Manager02 getInstance() {
        if (Instance == null) { //先判断当前对象是否为空,如果为空的话,就创建这个类的对象的实例
            //TODO 会存在线程安全问题
            //TODO 这里加载的还是不能保证线程安全,因为在if判断后,还是会被其他线程去执行下面加锁的代码的
            synchronized (Manager02.class) {
                if (Instance == null) {
                    try {
                        Thread.sleep(100);//模拟并发操作
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return Instance = new Manager02();
                }
            }
        }
        return Instance;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            new Thread(() -> System.out.println(Manager02.getInstance().hashCode())).start();
        }
    }
}
