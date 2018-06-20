package com.zn.designpattern.behavior.commond;

/**
 * 接受者角色类
 * Created by zhoun on 2018/6/20.
 **/
public class Receiver {

    /**
     * 真正执行命令相应的操作
     */
    public void action(){
        System.out.println("执行操作");
    }

}
