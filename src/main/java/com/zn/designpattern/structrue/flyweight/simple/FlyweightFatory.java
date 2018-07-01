package com.zn.designpattern.structrue.flyweight.simple;

import java.util.HashMap;
import java.util.Map;

/**
 * 享元工厂角色类
 * 由工厂方法产生所需要的享元对象
 * Created by zhoun on 2018/7/1.
 **/
public class FlyweightFatory {

    private Map<Character, Flyweight> files = new HashMap<>();

    public Flyweight factory(Character state) {
        //先从缓存中查找对象
        Flyweight flyweight = files.get(state);
        if (flyweight == null) {
            flyweight = new ConcreteFlyweight(state);
            files.put(state, flyweight);
        }
        return flyweight;
    }

}
