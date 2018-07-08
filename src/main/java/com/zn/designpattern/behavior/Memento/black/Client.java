package com.zn.designpattern.behavior.Memento.black;

/**
 * Created by zhoun on 2018/7/8.
 **/
public class Client {

    public static void main(String[] args) {
        Originator originator = new Originator();
        Caretaker caretaker = new Caretaker();
        originator.setState("on");
        caretaker.saveMemento(originator.createMemento());
        originator.setState("off");
        originator.restoreMememto(caretaker.retrieveMemento());
        System.out.println(originator.getState());
    }

}
