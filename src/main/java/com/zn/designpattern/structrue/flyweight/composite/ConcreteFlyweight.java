package com.zn.designpattern.structrue.flyweight.composite;

/**
 * Created by zhoun on 2018/7/1.
 **/
public class ConcreteFlyweight implements Flyweight {

    private Character intrinsicState;

    /**
     * 构造函数  内蕴状态作为参数传入
     * @param state
     */
    public ConcreteFlyweight(Character state) {
        this.intrinsicState = state;
    }

    @Override
    public void operation(String state) {
        // TODO Auto-generated method stub
        System.out.println("Intrinsic State = " + this.intrinsicState);
        System.out.println("Extrinsic State = " + state);
    }

}
