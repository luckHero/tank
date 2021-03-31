package com.hyq.tank;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {
    public static void main(String[] args) throws InterruptedException {
    //    Manager01 manager01 = new Manager01();
        //创建frame类,绘制画笔
         PropertyMgr.getInstance();
        TankFrame tankFrame = new TankFrame();
        int initTankCount = Integer.parseInt(String.valueOf(PropertyMgr.get("initTankCount")));
        //初始化坦克
        for (int i = 0; i < initTankCount; i++) {
            tankFrame.tanks.add(new Tank(60 + i * 100, 200, Dir.DOWN, tankFrame, Group.BAD));
        }
        // new Thread(()->new Audio(("audio/war1.wav")).loop()).start(); //开启另外一个线程开启音效
        while (true) {
            Thread.sleep(50);
            tankFrame.repaint();
        }
    }
}
