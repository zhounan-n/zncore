package com.zn.designpattern.statepattern;

/**
 * 抽象状态类
 */
public interface State {

    /**
     * 状态对应的处理
     * @param handle
     */
    void handle(String handle);
}
