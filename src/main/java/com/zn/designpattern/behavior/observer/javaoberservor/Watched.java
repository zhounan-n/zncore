package com.zn.designpattern.behavior.observer.javaoberservor;

import java.util.Observable;

/**
 * 被观察者
 * Created by zhoun on 2018/6/26.
 **/
public class Watched extends Observable {

    private String data = "";

    public String getData() {
        return data;
    }

    public void setData(String data) {
        if (!this.data.equals(data)) {
            this.data = data;
            setChanged();
        }
        notifyObservers();
    }

}
