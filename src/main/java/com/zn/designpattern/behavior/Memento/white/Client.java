package com.zn.designpattern.behavior.Memento.white;

/**
 * 白箱模式
 * Created by zhoun on 2018/7/8.
 **/
public class Client {

    public static void main(String[] args) {
        Originator originator = new Originator();
        Caretaker caretaker = new Caretaker();
        //改变负责人对象的状态
        originator.setState("On");
        //创建备忘录对象 并将发起人对象的状态存储起来
        caretaker.saveMemento(originator.createMemento());
        //修改发起人状态
        originator.setState("off");
        //恢复发起人对象的状态
        originator.restoreMemento(caretaker.retrieveMemento());

        System.out.println(originator.getState());
    }
}
