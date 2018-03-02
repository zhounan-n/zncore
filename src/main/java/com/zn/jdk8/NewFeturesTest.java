package com.zn.jdk8;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhoun on 2018/3/1.
 **/
public class NewFeturesTest {

    /**
     * lambda表达式优先用于定义功能接口在行内的实现
     * 既单个接口只有单个方法
     * 让匿名类不在需要，增添了简洁实用的函数式编程能力
     */
    public static void main(String[] args) {
        NewFeturesTest newFeturesTest = new NewFeturesTest();

        /**
         * 表达式语法：parameter -> expression body
         * 第一部分为一个用括号内用逗号分隔的参数列表，参数既函数式接口里面方法的参数；第二部分为一个->符号；第三部分为方法体，
         * 可以是表达式或代码块
         *
         * lambda表达式的几个重要特征：
         *  可选的声明类型，编译器可自动推断
         *  可选的参数周围的括号：你可以不用在括号内声明单个参数，但大多数情况下括号是需要的
         *  可选的大括号：如果方法体只有一个语句，可以不用括号括起来
         *  可选的返回关键字：
         *
         *
         *  函数式接口是一种只有一个方法的接口，函数式接口可以隐式转换成lambda表达式
         *  函数式接口的重要属性是我们能够使用lambda表达式实例化他们，lambda表达式能够让你将函数作为方法参数或者将代码作为数据对待
         *  lambda表达式的应用使得代码变得更紧凑，可读性增强，使并行操作大集合变得很方便，可以充分发挥多核cpu的优势，更易为多核处理器编写代码
         *
         */

        //带有类型声明的表达式
        MathOperation addition = (int a, int b) -> a + b;
        //没有类型声明的表达式
        MathOperation subtraction = (a, b) -> a - b;
        //带有大括号，带有返回语句的表达式
        MathOperation multiplication = (int a, int b) -> {
            return a * b;
        };
        //没有大括号和return语句的表达式
        MathOperation division = (int a, int b) -> a / b;

        //输出结果
        System.out.println("10 + 5 = " + newFeturesTest.operate(10, 5, addition));
        System.out.println("10 - 5 = " + newFeturesTest.operate(10, 5, subtraction));
        System.out.println("10 * 5 = " + newFeturesTest.operate(10, 5, multiplication));
        System.out.println("10 / 5 = " + newFeturesTest.operate(10, 5, division));

        //没有括号的表达式
        GreetingService greetingService1 = message ->
                System.out.println("hello " + message);
        GreetingService greetingService2 = (message) ->
                System.out.println("hello " + message);

        //调用sayMessage输出
        greetingService1.sayMessage("zhangsan");
        greetingService2.sayMessage("李四");


        /*****************方法引用*********************/
        /**
         * 方法引用通过::来描述
         * 构造器引用。语法是Class::new
         静态方法引用。语法是Class::static_method
         特定类的任意对象方法引用。它的语法是Class::method
         特定对象的方法引用，它的语法是instance::method

         */
        List names = new ArrayList<>();
        names.add("peter");
        names.add("linda");
        names.add("smith");
        names.add("zaker");

        names.forEach(System.out::println);
    }

    interface MathOperation {
        int operation(int a, int b);
    }

    private int operate(int a, int b, MathOperation mathOperation) {
        return mathOperation.operation(a, b);
    }

    interface GreetingService {
        void sayMessage(String message);
    }

    /**
     * 表达式作用域
     * 可访问static 修饰的成员变量.如果是final static修饰，不可再次赋值，static可再次赋值
     * 可访问表达式外层的final局部变量(不用声明为final,可隐性具有final语义)，不可再次赋值
     */


    /**
     * 方法引用:
     * java8中方法也是一个对象，可以用通过名字来引用，方法引用的唯一用途是支持lambda表达式
     */


}
