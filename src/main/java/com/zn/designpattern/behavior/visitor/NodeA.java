package com.zn.designpattern.behavior.visitor;


/**
 * 具体节点类A
 * Created by zhoun on 2018/6/26.
 **/
public class NodeA extends Node {


    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    /**
     * nodeA特有的方法
     */
    public String operationA() {
        return "optionA";
    }

}
