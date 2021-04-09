package com.hyq.tank;

import java.awt.*;

/**
 * @author lucky
 * @date 2021/4/7 21:11
 * 游戏物体
 * 名词定义抽象类
 * 形容词定义接口
 *
 * 问题 git stash 保存了历史队列但是转换出现异常\
 * 问题 git gitigonre文件未起作用
 * 问题 git stash apply stash@{num} 提示异常
 */
public abstract class GameObject {
    int x, y;//位置

    //画方法
    public abstract void paint(Graphics g);
}
