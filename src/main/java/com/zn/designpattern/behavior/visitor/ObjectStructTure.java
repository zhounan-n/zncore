package com.zn.designpattern.behavior.visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * 结构对象角色类
 * Created by zhoun on 2018/6/26.
 **/
public class ObjectStructTure {

    private List<Node> nodes = new ArrayList<>();

    /**
     * 执行方法操作
     */

    public void action(Visitor visitor) {
        for (Node node : nodes) {
            node.accept(visitor);
        }
    }

    public void add(Node node) {
        nodes.add(node);
    }

}
