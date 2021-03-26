package com.hyq.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * 继承Frame类,绘制画笔
 */
public class TankFrame extends Frame {
    //定义坦克的初始位置
    int x = 200, y = 200;
    //定义坦克的方向
    Dir dir = Dir.DOWN;
    static final int SPEED = 10;

    public TankFrame() {
        setSize(800, 600);//设置窗口大小
        setResizable(false); //设置窗口是否可以缩放
        setTitle("tank Game");//设置窗口的title
        setVisible(true);//设置窗口是否可见
        //添加键盘某个键盘监听的方法
        this.addKeyListener(new MyListener());
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
        //判断键盘按下的位置,
        switch (dir) {
            case LEFT:
                x -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case UP:
                y -= SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
        }
    }

    /**
     * 创建监听键盘方向的类
     * 重写键盘监听的方法
     */
    class MyListener extends KeyAdapter {
        boolean bL = false;
        boolean bR = false;
        boolean bU = false;
        boolean bD = false;

        //键盘被按下时调用
        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getExtendedKeyCode();
            System.out.println("键盘按下的方法...111" + keyCode);
            switch (keyCode) {
                case KeyEvent.VK_LEFT: //向左
                    bL = true;

                    //x-=20;
                    System.out.println(x + ":" + y);
                    break;
                case KeyEvent.VK_RIGHT://向右
                    //x+=20;
                    bR = true;
                    System.out.println(x + ":" + y);
                    break;
                case KeyEvent.VK_UP: //向上
                    // y-=20;
                    bU = true;
                    System.out.println(x + ":" + y);
                    break;
                case KeyEvent.VK_DOWN://向下
                    //y+=20;
                    bD = true;
                    System.out.println(x + ":" + y);
                    break;
            }
            setMainTankDir();
            //  repaint();//从新调用画布
        }

        //键盘被放开
        @Override
        public void keyReleased(KeyEvent e) {
            int keyCode = e.getExtendedKeyCode();
            switch (keyCode) {
                case KeyEvent.VK_LEFT: //向左
                    bL = false;
                    break;
                case KeyEvent.VK_RIGHT://向右
                    bR = false;
                    break;
                case KeyEvent.VK_UP: //向上
                    bU = false;
                    break;
                case KeyEvent.VK_DOWN: //向上
                    bD = false;
                    break;
            }
            setMainTankDir();
            System.out.println("键盘放开的方法....222" + keyCode);
        }

        //设置主站坦克的方向
        private void setMainTankDir() {
            //判断当前那个键盘被按下,设置坦克方向
            if (bL) dir = Dir.LEFT;
            if (bR) dir = Dir.RIGHT;
            if (bU) dir = Dir.UP;
            if (bD) dir = Dir.DOWN;
        }
    }

}
