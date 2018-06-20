package com.zn.designpattern.behavior.commond;

/**
 * 具体命令角色类
 * Created by zhoun on 2018/6/20.
 **/
public class ConcreteCommond implements Commond {

    /**
     * 持有相应的接收者对象
     */
    private Receiver receiver;

    public ConcreteCommond(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.action();
    }

}
