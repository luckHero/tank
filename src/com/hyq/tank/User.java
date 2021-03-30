package com.hyq.tank;

/**
 * @author lucky
 * @date 2021/3/30 20:55
 */
public class User {
    public static void s(){
        System.out.println("s");
    }
    public static void main(String[] args) {
        new Thread(()->new User().s());
    }
}
