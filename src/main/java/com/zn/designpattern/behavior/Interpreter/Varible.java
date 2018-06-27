package com.zn.designpattern.behavior.Interpreter;

/**
 * 有名变量
 * Created by zhoun on 2018/6/27.
 **/
public class Varible extends Expression {

    private String name;

    public Varible(String name) {
        this.name = name;
    }

    @Override
    public boolean interpret(Context ctx) {
        return ctx.lookup(this);
    }

    @Override
    public boolean equals(Object object) {
        if (object != null && object instanceof Varible) {
            return this.name.equals(((Varible) object).name);
        }
        return false;
    }

    @Override
    public int hashcode() {
        return this.toString().hashCode();
    }

    @Override
    public String toString() {
        return new String(name);
    }
}
