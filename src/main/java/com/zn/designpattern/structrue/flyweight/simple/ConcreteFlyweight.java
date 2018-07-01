package com.zn.designpattern.structrue.flyweight.simple;

/**
 * 具体享元角色类
 * Created by zhoun on 2018/7/1.
 **/
public class ConcreteFlyweight implements Flyweight {

    private Character intrinsicState;

    /**
     * 构造方法，内蕴状态作为参数传入
     *
     * @param character
     */
    public ConcreteFlyweight(Character character) {
        this.intrinsicState = character;
    }

    /**
     * 外蕴状态作为参数传入方法中，改变方法的行为，但是并不改变对象的内蕴状态
     * @param state
     */
    @Override
    public void operation(String state) {
        // TODO Auto-generated method stub
        System.out.println("Intrinsic State = " + this.intrinsicState);
        System.out.println("Extrinsic State = " + state);
    }

}
