package com.zn.designpattern.structrue.decorator;

/**
 * Created by zhoun on 2018/7/8.
 **/
public class ConcreteDecoratorB extends Decorator{

    public ConcreteDecoratorB(Component component) {
        super(component);
    }

    @Override
    public void sampleOperation() {
        super.sampleOperation();
        //写相关业务代码
    }
}
