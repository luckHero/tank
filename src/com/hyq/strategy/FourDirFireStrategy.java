package com.hyq.strategy;

import com.hyq.tank.*;

import java.util.Arrays;

/**
 * @author lucky
 * @date 2021/4/1 9:13
 */
public class FourDirFireStrategy implements FireStrategy {
    @Override
    public void fire(Tank tank) {
        int bx = tank.x + Tank.WIDTH / 2 - Bullet.WIDTH / 2; //调整子弹的宽度
        int by = tank.y + Tank.HEIGHT / 2 - Bullet.HEIGH / 2; //调整子弹的高度

        Dir[] dirs = Dir.values();
        Arrays.asList(dirs).forEach(d -> new Bullet(bx, by, d, tank.gameModel, tank.getGroup()));
        //new 出来子弹后,直接 通过构造方法加入到tankFrame
        new Bullet(bx, by, tank.getDir(), tank.gameModel, tank.getGroup());
        if (tank.getGroup() == Group.GOOD) {
            //TODO 这里new 出来的线程什么时候停止,还是一直继续运行
            new Thread(() -> {
                new Audio(("audio/tank_fire.wav")).play();
            }).start();
        }
    }
}
