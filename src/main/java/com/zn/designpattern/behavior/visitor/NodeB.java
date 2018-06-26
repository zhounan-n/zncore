package com.zn.designpattern.behavior.visitor;

/**
 * 具体节点类B
 * Created by zhoun on 2018/6/26.
 **/
public class NodeB extends Node {

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public String opeationB() {
        return "operationB";
    }

}
