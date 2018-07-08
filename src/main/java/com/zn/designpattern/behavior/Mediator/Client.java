package com.zn.designpattern.behavior.Mediator;

/**
 * 中介者模式：用一个中介者对象封装一系列的对象交互
 * 中介者模式使各对象之间不需要显示的进行交互
 * Created by zhoun on 2018/7/8.
 **/
public class Client {

    public static void main(String[] args) {
        AbstractColleague colleagueA = new ColleagueA();
        AbstractColleague colleagueB = new ColleagueB();
        System.out.println("-----设置A影响B-----");
        colleagueA.setNumber(100, colleagueB);
        System.out.println("Ade 值为：" + colleagueA.getNumber());
        System.out.println("Bde 值为：" + colleagueB.getNumber());

        System.out.println("-----设置B影响A-----");
        colleagueB.setNumber(200, colleagueA);
        System.out.println("Ade 值为：" + colleagueA.getNumber());
        System.out.println("Bde 值为：" + colleagueB.getNumber());

        /***----上面的做法类A类B直接发生关系---**/
        AbstractMediator am = new Mediator(colleagueA, colleagueB);
        colleagueA.setNumber(100, am);
        System.out.println("Ade 值为：" + colleagueA.getNumber());
        System.out.println("Bde 值为：" + colleagueB.getNumber());

        colleagueB.setNumber(100, am);
        System.out.println("Ade 值为：" + colleagueA.getNumber());
        System.out.println("Bde 值为：" + colleagueB.getNumber());


    }

}
