package com.zn.jdk8;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by zhoun on 2018/3/2.
 **/
public class NewFeturesTest2 {

    /**
     * 函数式接口：
     * 函数式接口通过单一的功能表现，java8定义了大量函数式接口来应用于lambda表达式(java.util.function)
     * 通过在接口里添加一个抽象方法，这个方法可以从接口中运行
     * 如果一个接口定义一个唯一的抽象方法，那么这个接口就是函数式接口
     * 引入了一个新的注解@FunctionalInterface,表示是一个函数式接口，非必须的会自动识别，加上注解则只能有一个抽象方法，否则编译器报错
     * 最好使用该注解
     */

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8);
        System.out.println("ALL of the numbers:");
        eval(list, n -> true);
        System.out.println("Even numbers:");
        eval(list, n -> n % 2 == 0);
        System.out.println("Numbers that greater than 5:");
        eval(list, n -> n > 5);
    }

    /**
     * Predicate函数式接口
     */
    public static void eval(List<Integer> list, Predicate<Integer> predicate) {
        for (Integer n : list) {
            if (predicate.test(n)) {
                System.out.println(n);
            }
        }
    }



}
