package com.zn.designpattern.behavior.commond;

/**
 * 命令模式：
 * 对象的行为模式 A聚合B B聚合C(C是接收者 真正执行方法)
 * <p>
 *     录音机是典型的命令模式，录音机按键把客户端与录音机的操作细节分开来
 *
 * Created by zhoun on 2018/6/20.
 **/
public class Client {

    public static void main(String[] args) {
        //创建接收者(真正执行方法)
        Receiver receiver = new Receiver();
        //创建命令对象，设定它的接收者
        Commond commond = new ConcreteCommond(receiver);
        //创建请求者 把命令对象设进去
        Invoker invoker = new Invoker(commond);
        invoker.action();

    }

}
