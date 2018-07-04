package com.zn.designpattern.behavior.iterator;


/**
 * 具体聚集角色类
 * Created by zhoun on 2018/7/4.
 **/
public class ConcreteAggregate extends Aggregate {

    private Object[] objArray;

    /**
     * 构造方法 传入聚合对象的具体内容
     *
     * @param objArray
     */
    public ConcreteAggregate(Object[] objArray) {
        this.objArray = objArray;
    }

    @Override
    Iterator createIterator() {
        return new ConcreteIterator(this);
    }

    /**
     * 取值方法 向外界提供元素
     */
    public Object getElement(int index) {
        if (index < objArray.length) {
            return objArray[index];
        }
        return -1;
    }

    /**
     * 向外界提供聚集的大小
     */
    public int size() {
        return objArray.length;
    }
}
