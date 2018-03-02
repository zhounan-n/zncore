package com.zn.jdk8;

/**
 * Created by zhoun on 2018/3/2.
 **/
public class NewFeturesTest3 {

    /**
     * 默认方法 ，向后兼容能力的实现
     * 有了默认方法，一个类实现两个相同默认方法的接口就变得可行了：
     * 第一个解决办法是创建一个自有的方法，来重写默认的实现
     * 第二个办法使用超类super来调用特定接口的默认方法
     */
    public static void main(String[] ars) {
        Younger younger = new Student();
        younger.print();
    }

    interface Younger {
        default void print() {
            System.out.println("I am a younger");
        }

        static void sayHi() {
            System.out.println("Younger is capital");
        }
    }

    interface Learner {
        default void print() {
            System.out.println("I am a learner");
        }
    }

    static class Student implements Younger, Learner {
        @Override
        public void print() {
            Younger.super.print();
            Learner.super.print();
            Younger.sayHi();
            System.out.println("I am a Student");
        }
    }

}
