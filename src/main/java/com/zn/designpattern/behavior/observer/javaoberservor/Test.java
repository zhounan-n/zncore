package com.zn.designpattern.behavior.observer.javaoberservor;

import java.util.Observer;

/**
 * Created by zhoun on 2018/6/26.
 **/
public class Test {

    public static void main(String[] args) {
        //创建被观察者对象
        Watched watched = new Watched();
        //创建观察者对象并将观察者对象标记
        Observer observer = new Watcher(watched);
        //给观察者状态赋值
        watched.setData("start");
        watched.setData("run");
        watched.setData("stop");
    }

}
