package com.zn.designpattern.behavior.visitor;

/**
 * 抽象访问者
 */
public interface Visitor {

    void visit(NodeA nodeA);

    void visit(NodeB nodeB);

}
