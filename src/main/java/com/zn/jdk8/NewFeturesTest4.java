package com.zn.jdk8;

import java.util.Optional;

/**
 * Created by zhoun on 2018/3/2.
 **/
public class NewFeturesTest4 {


    /**
     * Optional是一个容器对象，用于容纳非空对象，有很多使用的方法能处理一些像可用或不可用的值，而不是检查那些空值
     * 它是一个可以为null的容器对象,如果值存在isPresent()方法会返回true,调用get()方法会返回对象
     */
    public static void main(String[] args) {
        NewFeturesTest4 newFetureTest4 = new NewFeturesTest4();
        Integer value1 = null;
        Integer value2 = new Integer(5);
        //ofNullable允许传参时给出null
        Optional<Integer> a = Optional.ofNullable(value1);
        //如果传递的参数为null,那么of将报出空指针异常
        Optional<Integer> b = Optional.of(value2);
        System.out.println(newFetureTest4.sum(a, b));

    }

    public Integer sum(Optional<Integer> a, Optional<Integer> b) {
        //isPresent用于检查值是否存在

        System.out.println("first parameter is present " + a.isPresent());
        System.out.println("second parameter is present " + b.isPresent());

        //如果当前返回的是传入的默认值,orElse将返回它
        Integer value1 = a.orElse(new Integer(0));
        //get()用于获得值，前提是这个值必须存在
        Integer value2 = b.get();
        return value1 + value2;
    }

}
