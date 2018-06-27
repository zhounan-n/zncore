package com.zn.designpattern.behavior.Interpreter;

/**
 * 解释器模式：类的行为模式
 * 给定一个语言之后，解释器可定义出其文法的一种表示，并同时提供一个解释器，解释器可以用来解释这个语言的句子
 * Created by zhoun on 2018/6/27.
 **/
public class Client {

    public static void main(String[] args) {
        /*String a = "a";
        String b = "b";
        String c = a + b;
        String ab = "ab";
        System.out.println(c == ab);

        Integer aa = 2;
        Integer bb = 2;
        System.out.println(aa == bb);

        Integer aaa = new Integer(2);
        Integer bbb = new Integer(2);
        System.out.println(aaa == bbb);*/

        Context ctx = new Context();
        Varible x = new Varible("x");
        Varible y = new Varible("y");
        Constant c = new Constant(true);
        ctx.assign(x, false);
        ctx.assign(y, true);

        Expression exp = new Or(new And(c,x) , new And(y,new Not(x)));
        System.out.println("x=" + x.interpret(ctx));
        System.out.println("y=" + y.interpret(ctx));
        System.out.println(exp.toString() + "=" + exp.interpret(ctx));

    }

}
