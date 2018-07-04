package com.zn.designpattern.behavior.iterator;

/**
 * 具体迭代子角色类
 * Created by zhoun on 2018/7/4.
 **/
public class ConcreteIterator implements Iterator {

    /**
     * 持有被迭代的具体的聚合对象，
     */
    private ConcreteAggregate concreteAggregate;

    /**
     * 内部索引 记录当前被索引的位置
     */
    private int index;

    /**
     * 记录当前聚集对象的大小
     */
    private int size;

    public ConcreteIterator(ConcreteAggregate concreteAggregate) {
        this.concreteAggregate = concreteAggregate;
        this.size = concreteAggregate.size();
        index = 0;
    }


    @Override
    public void first() {
        index = 0;
    }

    @Override
    public void next() {
        if (index < size) {
            index++;
        }
    }

    @Override
    public boolean isDone() {
        return (index >= size);
    }

    @Override
    public Object currentItem() {
        return concreteAggregate.getElement(index);
    }


}
