package com.zn.designpattern.behavior.statepattern;

/**
 * 具体状态类
 * Created by zhoun on 2018/6/19.
 **/
public class StateA implements State {

    @Override
    public void handle(String handle) {
        System.out.println("A hello:" + handle);
    }

}
