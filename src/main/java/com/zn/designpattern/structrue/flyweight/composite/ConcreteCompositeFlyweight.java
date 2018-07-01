package com.zn.designpattern.structrue.flyweight.composite;

import java.util.HashMap;
import java.util.Map;

/**
 * 复合享元对象是由单纯享元对象通过复合而成的，因此它提供了add()这样的聚集管理方法。由于一个复合享元对象具有不同的聚集元素，
 * 这些聚集元素在复合享元对象被创建之后加入，这本身就意味着复合享元对象的状态是会改变的，因此复合享元对象是不能共享的。
 * Created by zhoun on 2018/7/1.
 **/
public class ConcreteCompositeFlyweight implements Flyweight{

    private Map<Character, Flyweight> files = new HashMap<Character, Flyweight>();

    /**
     * 增加一个新的单纯享元对象到聚集中
     */
    public void add(Character key, Flyweight fly) {
        files.put(key, fly);
    }

    /**
     * 外蕴状态作为参数传入到方法中
     */
    @Override
    public void operation(String state) {
        Flyweight fly = null;
        for (Object o : files.keySet()) {
            fly = files.get(o);
            fly.operation(state);
        }
    }


}
