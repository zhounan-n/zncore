package com.zn.designpattern.structrue.decorator;

/**
 * Created by zhoun on 2018/7/8.
 **/
public class ConcreteDecoratorA extends Decorator{

    public ConcreteDecoratorA(Component component) {
        super(component);
    }

    @Override
    public void sampleOperation() {
        super.sampleOperation();
        //写相关业务代码
    }
}
