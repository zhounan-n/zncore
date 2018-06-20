package com.zn.designpattern.behavior.statepattern;

/**
 * 具体状态类B
 * Created by zhoun on 2018/6/19.
 **/
public class StateB implements State {

    @Override
    public void handle(String handle) {
        System.out.println("B hello" + handle);
    }

}
