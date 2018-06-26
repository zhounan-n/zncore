package com.zn.designpattern.behavior.observer;

/**
 * 具体主题角色类
 * Created by zhoun on 2018/6/26.
 **/
public class ConcreteSubject extends Subject {

    private String state;

    public String getState() {
        return state;
    }

    public void change(String newState) {
        state = newState;
        System.out.println("主体状态为：" + state);
        //当状态变化时，通知各个观察者
        this.notifyObservers(newState);
    }

}
