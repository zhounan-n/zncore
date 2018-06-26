package com.zn.designpattern.behavior.observer;

/**
 * 抽象观察者角色类
 */
public interface Observer {

    /**
     * 更新接口
     * @param state 更新状态
     */
    void update(String state);
}
