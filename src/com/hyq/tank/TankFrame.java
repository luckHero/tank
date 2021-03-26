package com.hyq.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TankFrame extends Frame {
    int x = 200, y = 200;

    public TankFrame() {
        setSize(800, 600);//设置窗口大小
        setResizable(false); //设置窗口是否可以缩放
        setTitle("tank Game");//设置窗口的title
        setVisible(true);//设置窗口是否可见
        //添加键盘某个键盘监听的方法
        this.addKeyListener( new MyListener());
        //为当前类添加关闭窗口的监听事件,
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                //当点击窗口x,jvm 退出
                System.exit(0);
            }
        });
    }

    /**
     * 绘画窗口的方法
     *
     * @param g
     */
    @Override
    public void paint(Graphics g) {
        System.out.println("调用了paint 方法");
        g.fillRect(x, y, 60, 60);//改变方块的大小
       x += 50;
//        y += 50;

    }
    //处理键盘监听的方法
    class MyListener extends KeyAdapter {
        @Override
        //键盘被按下时调用
        public void keyPressed(KeyEvent e) {
            System.out.println("键盘按下的方法...111");
            x+=200;
          //  repaint();//从新调用画布
        }

        @Override
        //键盘被放开
        public void keyReleased(KeyEvent e) {
            System.out.println("键盘放开的方法....222");
        }
    }

}
