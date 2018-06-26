package com.zn.designpattern.behavior.visitor;

/**
 * 访问者模式
 * 封装一些施加于某种数据结构至上的元素，把数据结构和作用于数据结构上的行为分开
 * <p>
 * Created by zhoun on 2018/6/26.
 **/
public class Client {

    public static void main(String[] args) {
        //创建一个结构对象
        ObjectStructTure objectStructTure = new ObjectStructTure();
        //给结构增加一个节点A
        objectStructTure.add(new NodeA());
        //给结构增加一个节点B
        objectStructTure.add(new NodeB());
        //创建一个访问者
        Visitor visitor = new VisitorA();
        objectStructTure.action(visitor);

    }

}
