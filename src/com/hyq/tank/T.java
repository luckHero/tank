package com.hyq.tank;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class T {
    public static void main(String[] args) throws InterruptedException {
        //创建frame类,绘制画笔
        TankFrame tankFrame = new TankFrame();
        while (true) {
            Thread.sleep(1000);
            tankFrame.repaint();
        }
    }
}
