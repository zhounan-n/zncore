package com.zn.designpattern.behavior.Interpreter;

/**
 * 抽象表达式角色
 * Created by zhoun on 2018/6/27.
 **/
public abstract class Expression {

    /**
     * 以环境为准 本方法解释给定的任何一个表达式
     */
    public abstract boolean interpret(Context ctx);

    /**
     * 校验两个表达式在结构上是否相等
     *
     * @param object
     * @return
     */
    public abstract boolean equals(Object object);

    /**
     * 返回表达式的hashcode
     * @return
     */
    public abstract int hashcode();

    /**
     * 将标傲世转换为字符串
     * @return
     */
    public abstract String toString();
}
