package com.zn.designpattern.behavior.templatemethod;

/**
 * 模板方法模式
 * 是所有模式最常见的模式之一，是基于继承的代码服用技术之一
 *
 *  抽象模板-->具体模板
 *  模板方法：模板方法是定义在抽象类中的，是一组操作的集合或是一个总行为
 *  基本方法分三种：抽象方法  具体方法  钩子方法
 *      抽象方法：子类必须实现
 *      具体方法：子类可选择性重写
 *      钩子方法：子类可补充完善，一般钩子方法是空方法
 *  使用场景：
 *      计算存款利息：假设需要支持两种存款账号，即货币市场和定期存款，这两种存款利息是不同的，必须区分两种不同的账号类型
 *
 *
 *      模板方法在servlet中的应用：
 *          。HttpService类提供了一个service()方法，这个方法调用七个do方法中的一个或几个，完成对客户端调用的响应。这些do方法需要由HttpServlet的具体子类提供，因此这是典型的模板方法模式
 *
 * Created by zhoun on 2018/6/21.
 **/
public class Client {

    public static void main(String[] args){

    }

}
