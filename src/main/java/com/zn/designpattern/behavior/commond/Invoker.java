package com.zn.designpattern.behavior.commond;

/**
 * 请求者角色类
 * Created by zhoun on 2018/6/20.
 **/
public class Invoker {

    /**
     * 持有命令对象
     */
    private Commond commond;

    public Invoker(Commond commond) {
        this.commond = commond;
    }

    public void action() {
        commond.execute();
    }
}
