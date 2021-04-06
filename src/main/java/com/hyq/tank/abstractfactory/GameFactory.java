package com.hyq.tank.abstractfactory;

import com.hyq.tank.Dir;
import com.hyq.tank.Group;
import com.hyq.tank.TankFrame;

/**
 * @author lucky
 * @date 2021/4/6 8:16
 */
public abstract class GameFactory {
    public abstract BaseTank createTank(int x, int y, Dir dir, Group group, TankFrame tankFrame);

    public abstract BaseExplode createExplode(int x, int y, TankFrame tankFrame);

    public abstract BaseBullet createBullet(int x, int y,Dir dir, TankFrame tankFrame,Group group);
}
