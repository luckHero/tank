package com.hyq.tank.abstractfactory;

import com.hyq.tank.*;

/**
 * @author lucky
 * @date 2021/4/6 8:20
 * 默认工厂模式
 */
public class DefaultFactory extends GameFactory {
    @Override
    public BaseTank createTank(int x, int y, Dir dir, Group group, TankFrame tankFrame) {
        return new Tank(x, y, dir, tankFrame, group);
    }

    @Override
    public BaseExplode createExplode(int x, int y, TankFrame tankFrame) {
        return new Explode(x, y, tankFrame);
    }

    @Override
    public BaseBullet createBullet(int x, int y, TankFrame tankFrame) {
        return null;
    }
}
