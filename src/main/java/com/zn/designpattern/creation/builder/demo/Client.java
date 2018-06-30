package com.zn.designpattern.creation.builder.demo;

/**
 * Created by zhoun on 2018/6/30.
 **/
public class Client {

    public static void main(String[] args) {
        Builder builder = new WelcomeBuilder();
        Director director = new Director(builder);
        director.construct("aa", "bb");
    }

}
