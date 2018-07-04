package com.zn.designpattern.behavior.iterator.heixiang;

/**
 * 迭代子模式(又叫游标模式)：对象的行为模式，顺序的访问聚集中的元素而不必暴露聚集的内部表象
 * 迭代子模式有两种实现方式：白箱聚集于外禀迭代子模式和黑箱聚集于内禀迭代子
 * <p>
 * java聚集体现在java.util.collection
 * Created by zhoun on 2018/6/27.
 **/
public class Client {

    public static void main() {
        Client client = new Client();
        client.operation();
    }

    public void operation() {
        Object[] objArray = {"One", "Two", "Three", "Four", "Five", "Six"};
        //创建聚合对象
        Aggregate agg = new ConcreteAggregate(objArray);
        //循环输出聚合对象中的值
        Iterator it = agg.createIterator();
        while (!it.isDone()) {
            System.out.println(it.currentItem());
            it.next();
        }

    }

}
