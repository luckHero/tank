package com.hyq.tank;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        //创建frame类,绘制画笔
        TankFrame tankFrame = new TankFrame();
        //初始化坦克
        for (int i = 0; i < 5; i++) {
            tankFrame.tanks.add(new Tank(60 + i * 100, 200, Dir.DOWN, tankFrame,Group.BAD));
        }
        while (true) {
            Thread.sleep(50);
            tankFrame.repaint();
        }
    }
}
