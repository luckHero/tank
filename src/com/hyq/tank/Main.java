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
        new Thread(() -> new Audio(("audio/war1.wav")).play()).start(); //开启另外一个线程开启音效
        while (true) {
            Thread.sleep(50);
            tankFrame.repaint();
        }
    }
}
