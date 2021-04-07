package com.hyq.tank;

import java.awt.*;

/**
 * @author lucky
 * @date 2021/3/26 18:45
 * 子弹类
 */
public class Bullet {
    private int x, y;
    public static int WIDTH = ResourceMgr.bulletD.getWidth(); //子弹的宽度
    public static int HEIGH = ResourceMgr.bulletD.getHeight();//子弹的高度
    private static final int SPEED = 6;//子弹速度
    boolean living = true;//子弹是否还活着
    private Dir dir;//子弹的方向
    private GameModel gameModel = null;
    private Group group = Group.BAD;
    Rectangle rectangle = new Rectangle();//计算坦克图片矩形的类

    public Bullet(int x, int y, Dir dir, GameModel gameModel, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.gameModel = gameModel;
        this.group = group;
        rectangle.x = this.x;
        rectangle.y = this.y;
        rectangle.width = WIDTH;
        rectangle.height = HEIGH;
        gameModel.bullets.add(this);
    }

    //绘制子弹的方法
    public void paint(Graphics g) {
        if (!living) {
            gameModel.bullets.remove(this);
        }
        switch (dir) {
            case LEFT:
                g.drawImage(ResourceMgr.bulletL, x, y, null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.bulletR, x, y, null);
                break;
            case UP:
                g.drawImage(ResourceMgr.bulletU, x, y, null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.bulletD, x, y, null);
                break;
            default:
                g.drawImage(ResourceMgr.bulletD, x, y, null);
                break;
        }
//        g.setColor(Color.RED);//设置子弹颜色
//        g.fillOval(x, y, WIDTH, HEIGHT);//绘制一个矩形的内切圆
        move();//移动的方法
    }

    //子弹移动的方法
    private void move() {
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
        rectangle.x = this.x;
        rectangle.y = this.y;
//        System.out.println("子弹x:" + x + ",子弹y:" + y);
        //  System.out.println("TankFrame.GAME_WIDTH:"+TankFrame.GAME_WIDTH +"TankFrame.HEIGHT:"+TankFrame.HEIGHT);
        if (x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT) living = false;//超出范围将子弹属性设为死亡
    }

    //子弹和坦克进行碰撞检测
    public void collideWith(Tank tank) {
        if (this.group == tank.getGroup()) return; //判断子弹的group 和坦克的group
        //TODO 用一个rect 记录自己的位置 ,这里需要优化 每次子弹都会和坦克进行碰撞
//        Rectangle bulletRect = new Rectangle(this.x, this.y, WIDTH, HEIGH); //子弹的矩形
//        Rectangle tankRect = new Rectangle(tank.getX(), tank.getY(), Tank.WIDTH, Tank.HEIGHT); //坦克的矩形
        if (rectangle.intersects(tank.rectangle)) { //判断子弹是否与坦克相交
            tank.die();//坦克死亡
            this.die();//子弹死亡
            int eX = tank.getX() + Tank.WIDTH / 2 - Explode.WIDTH / 2;
            int eY = tank.getY() + Tank.HEIGHT / 2 - Explode.HEIGHT / 2;
            gameModel.explodes.add(new Explode(eX, eY, gameModel));
            new Thread(() -> new Audio("audio/explode.wav").play()).start();
        }
    }

    //子弹死亡的方法
    private void die() {
        this.living = false;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
