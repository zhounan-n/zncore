package com.zn.designpattern.creation.builder;

/**
 * 抽象建造者
 * Created by zhoun on 2018/6/30.
 **/
public interface Builder {

    void buildPart1();

    void buildPart2();

    Product retrieveResult();
}
