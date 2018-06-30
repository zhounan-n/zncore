package com.zn.designpattern.creation.builder;

/**
 * 产品类
 * Created by zhoun on 2018/6/30.
 **/
public class Product {

    private String part1;
    private String part2;

    public String getPart1() {
        return part1;
    }

    public void setPart1(String part1) {
        this.part1 = part1;
    }

    public String getPart2() {
        return part2;
    }

    public void setPart2(String part2) {
        this.part2 = part2;
    }
}
