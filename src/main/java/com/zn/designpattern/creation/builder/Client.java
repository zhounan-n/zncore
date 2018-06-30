package com.zn.designpattern.creation.builder;

/**
 * 建造者模式:
 * 对象的创建模式,将产品的内部表象与产品的生产过程分割开来，从而可以使一个建造过程生成具有不同的内部表象的产品
 *
 * 使用场景：
 *      电子杂志的订阅，开始订发送欢迎邮件，结束订阅发送欢送邮件
 *
 * 什么情况下使用建造者模式？
 *      1:需要生成的产品对象有复杂的内部结构，每一个内部成分本身可以是一个对象，也可以是对象的组成部分
 *      2: 需要生成的产品对象的属性相互依赖。建造模式可以强制实行一种分步骤进行的建造过程，因此，如果产品对象的一个属性必须在另一个属性被赋值之后才可以被赋值，使用建造模式是一个很好的设计思想。
 *      3:在对象创建过程中会使用到系统中的其他一些对象，这些对象在产品对象的创建过程中不易得到。
 * Created by zhoun on 2018/6/29.
 **/
public class Client {

    public static void main(String[] args) {
        Builder builder = new ConcreteBuilder();
        Director director = new Director(builder);
        director.construct();
        Product product = builder.retrieveResult();
        //springboot配置对象设置 appid appkey属于建造者模式？
        System.out.println(product.getPart1());
        System.out.println(product.getPart2());
    }

}
