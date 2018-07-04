package com.zn.designpattern.behavior.iterator.heixiang;


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
        return new ConcreteIterator();
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


    /**
     * 内部成员类，具体迭代子类
     */
    private class ConcreteIterator implements Iterator {
        //内部索引，记录当前迭代到的索引位置
        private int index;
        //记录当前聚集对象的大小
        private int size;

        /**
         * 构造函数
         */
        public ConcreteIterator() {

            this.size = objArray.length;
            index = 0;
        }

        /**
         * 迭代方法：返还当前元素
         */
        @Override
        public Object currentItem() {
            return objArray[index];
        }

        /**
         * 迭代方法：移动到第一个元素
         */
        @Override
        public void first() {

            index = 0;
        }

        /**
         * 迭代方法：是否为最后一个元素
         */
        @Override
        public boolean isDone() {
            return (index >= size);
        }

        /**
         * 迭代方法：移动到下一个元素
         */
        @Override
        public void next() {

            if (index < size) {
                index++;
            }
        }
    }

}
