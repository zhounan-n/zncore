package com.zn.designpattern.structrue.prototype;

/**
 * Created by zhoun on 2018/6/28.
 **/
public class ConcretePrototype2 implements Prototype{


    private String name;
    public Prototype clone(){
        ConcretePrototype2 prototype = new ConcretePrototype2();
        prototype.setName(this.name);
        return prototype;
    }
    @Override
    public String toString(){
        return "Now in Prototype2 , name = " + this.name;
    }
    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }


}
