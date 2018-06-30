package com.zn.designpattern.creation.builder.demo;

/**
 * GoodbyeBuilder具体建造者类
 * Created by zhoun on 2018/6/30.
 **/
public class GoodbyeBuilder extends Builder {


    public GoodbyeBuilder() {
        msg = new GoodByeMessage();
    }

    @Override
    public void buildSubject() {
        msg.setSubject("欢送");
    }

    @Override
    public void buildContent() {
        msg.setBody("欢送");
    }

    @Override
    public void buildTo(String to) {
        super.buildTo(to);
    }

    @Override
    public void buildFrom(String from) {
        super.buildFrom(from);
    }

    @Override
    public void buildSendDate() {
        super.buildSendDate();
    }

    @Override
    public void sendMessage() {
        super.sendMessage();
    }
}
