package com.zn.designpattern.creation.builder.demo;

/**
 * 具体产品类WelcomeMessage
 * Created by zhoun on 2018/6/30.
 **/
public class WelcomeMessage extends AutoMessage {

    public WelcomeMessage() {
        System.out.println("发送欢迎信息");
    }

}
