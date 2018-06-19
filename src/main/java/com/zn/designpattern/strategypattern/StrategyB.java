package com.zn.designpattern.strategypattern;

/**
 * 策略B类
 * Created by zhoun on 2018/6/19.
 **/
public class StrategyB implements Strategy{

    @Override
    public void calculate() {
        System.out.println("75折");
    }

}
