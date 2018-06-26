package com.zn.designpattern.behavior.observer.javaoberservor;

import java.util.Observable;
import java.util.Observer;

/**
 * 观察者
 * Created by zhoun on 2018/6/26.
 **/
public class Watcher implements Observer {

    public Watcher(Observable o) {
        o.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("状态发生改变：" + ((Watched) o).getData());
    }

}
