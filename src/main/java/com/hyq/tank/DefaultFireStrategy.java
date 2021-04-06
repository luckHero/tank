package com.hyq.tank;

/**
 * @author lucky
 * @date 2021/4/1 8:12
 */

/**
 * 策略设计模式,将坦克的发射子弹的数量需要传递策略实现
 * 于是将default Fire Strategy 设置成单列
 */
public class DefaultFireStrategy implements FireStrategy {
//    private static DefaultFireStrategy Instance = null;

    /**
     * 将构造方法私有
     */
    private DefaultFireStrategy() {
    }

    /**
     * 静态内部类的方式实现单例模式
     */
    private static class DefaultFireStrategyHolder {
        private final static DefaultFireStrategy Instance = new DefaultFireStrategy();
    }

    /**
     *  该方法必须静态
     * @return
     */
    public static DefaultFireStrategy getInstance() {
        return DefaultFireStrategyHolder.Instance;
    }

    /**
     * 懒汉模式的单例
     * 双重判断解决线程安全问题
     *
     * @return
     */
//    public static DefaultFireStrategy getInstance() {
//        if (Instance == null) {
//            synchronized (DefaultFireStrategy.class) {
//                if (Instance == null) {
//                    return Instance = new DefaultFireStrategy();
//                }
//            }
//        }
//        return Instance;
//    }
    @Override
    public void fire(Tank tank) {
        int bx = tank.x + Tank.WIDTH / 2 - Bullet.WIDTH / 2; //调整子弹的宽度
        int by = tank.y + Tank.HEIGHT / 2 - Bullet.HEIGH / 2; //调整子弹的高度
        if (tank.getGroup() == Group.GOOD) {

            new Thread(() -> {
                new Audio(("audio/tank_fire.wav")).play();
            }).start();
//            try {
//                Thread.sleep(60);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            thread.stop();
        }
        //new 出来子弹后,直接 通过构造方法加入到tankFrame
        new Bullet(bx, by, tank.getDir(), tank.tankFrame, tank.getGroup());//发射子弹
    }
}
