package com.zn.designpattern.creation.builder;

/**
 * 导演者类
 * Created by zhoun on 2018/6/30.
 **/
public class Director {

    /**
     * 持有当前所需要的构建者对象
     */
    private Builder builder;

    public Director(Builder builder) {
        this.builder = builder;
    }

    /**
     * 产品构造方法，负责调用各个零件构造方法
     */
    public void construct() {
        builder.buildPart1();
        builder.buildPart2();
    }

}
