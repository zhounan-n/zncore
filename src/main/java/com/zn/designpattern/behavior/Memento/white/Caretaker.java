package com.zn.designpattern.behavior.Memento.white;

/**
 * 负责人角色类
 * Created by zhoun on 2018/7/8.
 **/
public class Caretaker {

    private Memento memento;

    /**
     * 备忘录的取值方法
     */
    public Memento retrieveMemento() {
        return this.memento;
    }

    /**
     * 备忘录的赋值方法
     */
    public void saveMemento(Memento memento) {
        this.memento = memento;
    }
}
