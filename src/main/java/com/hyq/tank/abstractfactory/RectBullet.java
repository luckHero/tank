package com.hyq.tank.abstractfactory;

import com.hyq.tank.*;

import java.awt.*;

/**
 * @author lucky
 * @date 2021/4/6 9:10
 */
public class RectBullet  extends  BaseBullet{
    private int x, y;
    public static int WIDTH = ResourceMgr.bulletD.getWidth(); //子弹的宽度
    public static int HEIGH = ResourceMgr.bulletD.getHeight();//子弹的高度
    private static final int SPEED = 6;//子弹速度
    boolean living = true;//子弹是否还活着
    private Dir dir;//子弹的方向
    private TankFrame tankFrame = null;
    private Group group = Group.BAD;
    Rectangle rectangle = new Rectangle();//计算坦克图片矩形的类

    public RectBullet(int x, int y, Dir dir, TankFrame tankFrame, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tankFrame = tankFrame;
        this.group = group;
        rectangle.x = this.x;
        rectangle.y = this.y;
        rectangle.width = WIDTH;
        rectangle.height = HEIGH;
        tankFrame.bullets.add(this);
    }

    //绘制子弹的方法
    public void paint(Graphics g) {
        if (!living) {
            tankFrame.bullets.remove(this);
        }
        Color color = g.getColor();
        g.setColor(Color.YELLOW);
        g.fillRect(x,y,20,20);
        g.setColor(color);
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
        // System.out.println("子弹x:" + x + ",子弹y:" + y);
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
            tankFrame.explodes.add(tankFrame.gameFactory.createExplode(eX,eY,tankFrame));
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
