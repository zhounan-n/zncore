package com.zn.designpattern.behavior.visitor;

/**
 * 抽象节点
 * Created by zhoun on 2018/6/26.
 **/
public abstract class Node {

    /**
     * 接受操作
     */
    public abstract void accept(Visitor visitor);

}
