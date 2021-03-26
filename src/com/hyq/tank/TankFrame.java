package com.hyq.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * 继承Frame类
 */
public class TankFrame extends Frame {
    Tank myTank = new Tank(200, 200, Dir.DOWN,this);
    Bullet bullet = new Bullet(300, 300, Dir.DOWN);
    static final int GAME_WIDTH = 800, GAME_HEIGHT = 600;//抽出游戏高度和宽度

    public TankFrame() {
        setSize(GAME_WIDTH, GAME_HEIGHT);//设置窗口大小
        setResizable(false); //设置窗口是否可以缩放
        setTitle("坦克大战");//设置窗口的title
        setVisible(true);//设置窗口是否可见
        // setBackground(Color.CYAN);
        this.addKeyListener(new MyListener());  //添加键盘某个键盘监听的方法
        addWindowListener(new WindowAdapter() {  //为当前类添加关闭窗口的监听事件,
            @Override
            public void windowClosing(WindowEvent e) {
                //当点击窗口x,jvm 退出
                System.exit(0);
            }
        });
    }

    /**
     *  双缓冲概念解决屏幕闪烁
     *  1.这里有两只画笔,一个系统创建的画笔,首先先将图片背景颜色画到内存中
     *  2.然后利用这个
     */
    Image offScreenImg = null; //定义一张图片在内存中,未画出
    @Override
    public void update(Graphics g) {//这里是系统的画笔
        if (offScreenImg == null) {
            offScreenImg = this.createImage(GAME_WIDTH, GAME_HEIGHT);//在内存创建图片出来
        }
        Graphics gOffScreen = offScreenImg.getGraphics();//用图片拿到画笔
        Color color = gOffScreen.getColor();//设置color
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0,0,GAME_WIDTH,GAME_HEIGHT);//绘制这个图片
        gOffScreen.setColor(color);//设置回来原来的颜色
        paint(gOffScreen);
        g.drawImage(offScreenImg,0,0,null);//将内存中的图片画到内存中
    }

    /**
     * 绘画窗口的方法
     *
     * @param g
     */
    @Override
    public void paint(Graphics g) {//这里绘制的画笔是图片的,会画到内存中
        System.out.println("调用了paint 方法");
        myTank.paint(g);//绘制坦克
        bullet.paint(g);//绘制子弹
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

        //控制坦克方向
        @Override
        public void keyPressed(KeyEvent e) { //键盘被按下时调用
            int keyCode = e.getExtendedKeyCode();
            System.out.println("键盘按下的方法...111" + keyCode);
            switch (keyCode) {
                case KeyEvent.VK_LEFT: //向左
                    bL = true;
                    break;
                case KeyEvent.VK_RIGHT://向右
                    bR = true;
                    break;
                case KeyEvent.VK_UP: //向上
                    bU = true;
                    break;
                case KeyEvent.VK_DOWN://向下
                    bD = true;
                    break;
                case KeyEvent.VK_CONTROL: //ctrl键发射子弹
                    myTank.fire();//发射子弹
                    default:
                        break;
            }
            setMainTankDir();//
            //  repaint();//从新调用画布
        }

        //控制键盘放开,坦克方向改变
        @Override
        public void keyReleased(KeyEvent e) { //键盘被放开
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

        //移动主站坦克的方向
        private void setMainTankDir() {
            if (!bL && !bR && !bU && !bD) {//判断坦克是否静止
                myTank.setMoving(false);
            } else {
                myTank.setMoving(true);//让坦克移动
                //判断当前那个键盘被按下,设置坦克方向
                if (bL) myTank.setDir(Dir.LEFT);
                if (bR) myTank.setDir(Dir.RIGHT);
                if (bU) myTank.setDir(Dir.UP);
                if (bD) myTank.setDir(Dir.DOWN);
            }
        }
    }

}
