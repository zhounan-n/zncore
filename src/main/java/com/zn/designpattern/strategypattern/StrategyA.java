package com.zn.designpattern.strategypattern;

/**
 * 策略具体类A
 * Created by zhoun on 2018/6/19.
 **/
public class StrategyA implements Strategy{

    @Override
    public void calculate() {
        System.out.println("打五折");
    }

}
