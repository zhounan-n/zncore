package com.zn.designpattern.structrue.decorator;

/**
 * 装饰角色
 * Created by zhoun on 2018/7/8.
 **/
public class Decorator implements Component {
    private Component component;

    public Decorator(Component component) {
        this.component = component;
    }

    @Override
    public void sampleOperation() {
        //委派给构件
        component.sampleOperation();
    }
}
