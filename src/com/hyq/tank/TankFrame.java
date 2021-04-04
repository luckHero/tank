package com.hyq.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 继承Frame类,窗口类
 */
public class TankFrame extends Frame {
    Tank myTank = new Tank(200, 500, Dir.UP, this, Group.GOOD);//坦克类
    List<Bullet> bullets = new ArrayList<>(); //设置子弹容器
    //Bullet bullet = new Bullet(300, 300, Dir.DOWN, this); //子弹类
    List<Tank> tanks = new ArrayList<>();//设置敌方坦克的容器
    List<Explode> explodes = new ArrayList<>();//爆炸的集合
    static final int GAME_WIDTH = 1080, GAME_HEIGHT = 960;//游戏高度和宽度
//    Explode e=new Explode(100,100,this); //画一个爆炸的效果

    //tankFrame的空参构造,用来创建窗口类的
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
     * 双缓冲概念解决屏幕闪烁
     * 1.这里有两只画笔,一个系统创建的画笔,首先先将图片背景颜色画到内存中
     * 2.然后利用这个
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
        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);//绘制这个图片
        gOffScreen.setColor(color);//设置回来原来的颜色
        paint(gOffScreen);
        g.drawImage(offScreenImg, 0, 0, null);//将内存中的图片画到内存中
    }

    /**
     * 绘画窗口的方法
     *
     * @param g
     */
    @Override
    public void paint(Graphics g) {//这里绘制的画笔是图片的,会画到内存中
        Color color = g.getColor();
        g.setColor(Color.white);
        g.drawString("子弹的数量:" + bullets.size(), 10, 60);
        g.drawString("坦克的数量:" + tanks.size(), 10, 80);
        g.drawString("爆炸的数量:" + explodes.size(), 10, 100);
        g.setColor(color);
        myTank.paint(g);//绘制坦克
        //TODO 这里有并发修改异常
//        bullets.forEach(b -> {//画子弹
//            b.paint(g);
//        });
        //TODO 解决方式一
        //绘画子弹的方法
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).paint(g);
        }
        //TODO解决方法二  绘画子弹的方法
//        Iterator<Bullet> iterator = bullets.iterator();
//        for (bullets.iterator(); iterator.hasNext(); ) {
//            Bullet bullet = iterator.next();
        //绘画敌人坦克
        for (int i = 0; i < tanks.size(); i++) {
            tanks.get(i).paint(g);
        }
        //画爆炸
        for (int i = 0; i < explodes.size(); i++) {
            explodes.get(i).paint(g);
        }
        //碰撞检测的方法
        for (int i = 0; i < bullets.size(); i++) {
            for (int j = 0; j < tanks.size(); j++) {
                bullets.get(i).collideWith(tanks.get(j));
            }
        }

//            /**
//             *  成员变量被private修饰,在其他类不能不引用,那这个成员变量的在内存中是放在哪里的
//             *  为什么通过反射能够拿到类的私有成员变量
//             */
//            if (!bullet.living) iterator.remove();
//        }
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
          //  System.out.println("键盘按下的方法...111" + keyCode);
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
                case KeyEvent.VK_CONTROL: //ctrl键发射子弹
                    myTank.fire(new FourDirFireStrategy());//发射子弹 将主站坦克发射子弹改成向四周开火
                default:
                    break;
            }
            setMainTankDir();//设置坦克移动方向
            //   System.out.println("键盘放开的方法....222" + keyCode);
        }

        //TODO 有bug,主站坦克会不受控制随意移动
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
