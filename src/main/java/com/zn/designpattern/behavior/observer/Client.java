package com.zn.designpattern.behavior.observer;

/**
 * 观察者模式
 * 对象的行为模式,又叫发布 订阅
 * 一个对象的状态变化后 另一个对象也跟着变化
 * 推模型和拉模型：
 *   推模型：主题对象向观察者推送主题的详细信息(知道观察者需要的数据)
 *   拉模型：在通知观察者的时候只传递少量信息，如果观察者需要更多的信息让其主动获取(不知道观察者需要的数据)
 *
 *   java对观察者模式的支持：
 *      java.util.Observer  java.util.Observable
 * Created by zhoun on 2018/6/26.
 **/
public class Client {

    public static void main(String[] args) {
        //创建主题对象
        ConcreteSubject subject = new ConcreteSubject();
        //创建观察者对象
        Observer observer = new ConcreteObserver();
        //将观察者对象登记到主题对象上
        subject.attach(observer);
        //改变主题对象的状态
        subject.change("new status");

    }

}
