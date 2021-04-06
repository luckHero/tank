package com.hyq.tank.abstractfactory;

import com.hyq.tank.Dir;
import com.hyq.tank.Group;
import com.hyq.tank.TankFrame;

import java.awt.*;

/**
 * @author lucky
 * @date 2021/4/6 8:50
 * 方形爆炸工厂
 */
public class RectFactory extends GameFactory {

    @Override
    public BaseTank createTank(int x, int y, Dir dir, Group group, TankFrame tankFrame) {
        return null;
    }

    //创建基础子弹类
    @Override
    public BaseExplode createExplode(int x, int y, TankFrame tankFrame) {
        return new RectExplode(x, y, tankFrame);
    }

    @Override
    public BaseBullet createBullet(int x, int y, TankFrame tankFrame) {
        return null;
    }
}
