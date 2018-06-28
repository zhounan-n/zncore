package com.zn.designpattern.structrue.AbstractFactory;

/**
 * 抽象工厂模式是对象的创建模式
 * 假设一个子系统需要一些产品对象，而这些产品又属于一个以上的等级结构，那么为了将这些消费产品对象的责任和
 * 创建这些产品对象的责任划分开来，可以引进抽象工厂模式
 * Created by zhoun on 2018/6/28.
 **/
public class Client {

    public static void main(String[] args) {
        //创建装机工程师对象
        ComputeEngineer computeEngineer = new ComputeEngineer();
        //客户选择并创建需要选择的对象
        AbstractFactory af = new IntelFactory();
        //告诉装机工程师自己选择的产品，让装机工程师组装电脑
        computeEngineer.makeComputer(af);
    }

}
