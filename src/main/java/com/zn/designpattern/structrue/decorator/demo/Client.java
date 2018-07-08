package com.zn.designpattern.structrue.decorator.demo;

/**
 * Created by zhoun on 2018/7/8.
 **/
public class Client {

    public static void main(String[] args) {
        TheGreatestSage sage = new Monkey();
        //第一种写法
        TheGreatestSage bird = new Bird(sage);
        TheGreatestSage fish = new Fish(bird);

        //第二种写法
        //TheGreatestSage fish = new Fish(sage);
        fish.move();

    }

}
