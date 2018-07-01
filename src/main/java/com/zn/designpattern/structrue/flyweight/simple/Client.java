package com.zn.designpattern.structrue.flyweight.simple;

/**
 * Created by zhoun on 2018/7/1.
 **/
public class Client {

    public static void main() {
        FlyweightFatory flyweightFatory = new FlyweightFatory();
        Flyweight flyweight = flyweightFatory.factory(new Character('q'));
        flyweight.operation("first call");

        flyweight = flyweightFatory.factory(new Character('b'));
        flyweight.operation("second call");

        flyweight = flyweightFatory.factory(new Character('c'));
        flyweight.operation("third call");


    }

}
