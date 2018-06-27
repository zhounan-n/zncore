package com.zn.designpattern.behavior.Interpreter;

/**
 * Created by zhoun on 2018/6/27.
 **/
public class Or extends Expression {
    private Expression left, right;

    public Or(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public int hashcode() {
        return this.toString().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof Or) {
            return this.left.equals(((Or) obj).left) && this.right.equals(((Or) obj).right);
        }
        return false;
    }


    @Override
    public boolean interpret(Context ctx) {
        return left.interpret(ctx) || right.interpret(ctx);
    }

    @Override
    public String toString() {
        return "(" + left.toString() + " OR " + right.toString() + ")";
    }
}
