package com.zn.designpattern.behavior.Interpreter;


/**
 * 一个Constant对象代表一个布尔常量
 * Created by zhoun on 2018/6/27.
 **/
public class Constant extends Expression {
    private boolean value;

    public Constant(boolean value) {
        this.value = value;
    }


    @Override
    public boolean interpret(Context ctx) {
        return value;
    }

    @Override
    public boolean equals(Object object) {
        if (object != null && object instanceof Constant) {
            return this.value == ((Constant) object).value;
        }
        return false;
    }

    @Override
    public int hashcode() {
        return this.toString().hashCode();
    }

    @Override
    public String toString() {
        return new Boolean(value).toString();
    }
}
