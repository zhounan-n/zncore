package com.zn.designpattern.behavior.observer;

/**
 * 具体观察者角色类
 * Created by zhoun on 2018/6/26.
 **/
public class ConcreteObserver implements Observer {

    /**
     * 观察者对象状态
     */
    private String observerState;

    @Override
    public void update(String state) {
        //更新观察者的状态，使其与目标的状态保持一致
        observerState = state;
        System.out.println("状态为： " + observerState);

    }


}
