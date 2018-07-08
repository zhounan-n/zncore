package com.zn.designpattern.structrue.decorator.demo;

/**
 * 具体构件角色 大圣本尊猢狲类
 * Created by zhoun on 2018/7/8.
 **/
public class Monkey implements TheGreatestSage {

    @Override
    public void move() {
        //代码
        System.out.println("monkey move");
    }

}
