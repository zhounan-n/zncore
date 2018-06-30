package com.zn.designpattern.creation.builder.demo;

/**
 * 导演者
 * Created by zhoun on 2018/6/30.
 **/
public class Director {

    Builder builder;

    public Director(Builder builder) {
        this.builder = builder;
    }

    /**
     * 产品构造方法  负责各零件的构造
     */
    public void construct(String toAddress,String fromAddress) {
        this.builder.buildTo(toAddress);
        this.builder.buildFrom(fromAddress);
        this.builder.buildSubject();
        this.builder.buildContent();
        this.builder.buildSendDate();
        this.builder.sendMessage();
    }


}
