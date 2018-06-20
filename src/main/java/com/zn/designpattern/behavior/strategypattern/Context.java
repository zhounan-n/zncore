package com.zn.designpattern.behavior.strategypattern;

/**
 * Created by zhoun on 2018/6/19.
 **/
public class Context {

    /**
     * 次有一个具体策略的对象
     */
    private Strategy strategy;

    /**
     * 传入具体的策略对象
     * @param strategy
     */
    public Context(Strategy strategy) {
        this.strategy = strategy;
    }

    /**
     * 策略方法
     */
    public void contextInterface() {
        strategy.calculate();
    }

}
