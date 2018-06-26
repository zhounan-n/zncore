package com.zn.designpattern.behavior.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 抽象主题角色类
 * Created by zhoun on 2018/6/26.
 **/
public abstract class Subject {

    /**
     * 用来保存注册的观察者对象
     */
    private List<Observer> list = new ArrayList<>();

    /**
     * 注册观察者对象
     */
    public void attach(Observer observer) {
        list.add(observer);
        System.out.println("attach a objectserver");
    }

    /**
     * 删除观察者对象
     */
    public void detach(Observer observer) {
        list.remove(observer);
    }

    /**
     * 通知所有注册的观察者对象
     */
    public void notifyObservers(String newState) {
        for (Observer observer : list) {
            observer.update(newState);
        }
    }

}
