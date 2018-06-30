package com.zn.designpattern.creation.builder.demo;

/**
 * 具体建造者
 * Created by zhoun on 2018/6/30.
 **/
public class WelcomeBuilder extends Builder {

    public WelcomeBuilder() {
        msg = new WelcomeMessage();
    }

    @Override
    public void buildSubject() {
        msg.setSubject("欢迎标题");
    }

    @Override
    public void buildContent() {
        msg.setBody("欢迎内容");
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
