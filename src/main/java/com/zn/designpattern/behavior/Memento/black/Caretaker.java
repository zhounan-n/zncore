package com.zn.designpattern.behavior.Memento.black;

import com.zn.designpattern.behavior.Memento.white.Memento;

/**
 * 负责人角色类
 * Created by zhoun on 2018/7/8.
 **/
public class Caretaker {

    private MementoIF memento;

    /**
     * 备忘录的取值方法
     */
    public MementoIF retrieveMemento() {
        return this.memento;
    }

    /**
     * 备忘录的赋值方法
     */
    public void saveMemento(MementoIF memento) {
        this.memento = memento;
    }
}
