package com.hyq.tank.abstractfactory;

import com.hyq.tank.Tank;

import java.awt.*;

/**
 * @author lucky
 * @date 2021/4/6 8:19
 */
public abstract class BaseBullet {
    public abstract void paint(Graphics g);

    public abstract void collideWith(Tank tank);
}
