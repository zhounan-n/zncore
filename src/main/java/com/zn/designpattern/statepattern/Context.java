package com.zn.designpattern.statepattern;

/**
 * 状态模式
 * 环境角色
 * Created by zhoun on 2018/6/19.
 **/
public class Context {


    /**
     * 拥有一个State类型的对象实例
     */
    private State state;

    public void setStatus(State state) {
        this.state = state;
    }

    /**
     * 用户感兴趣的接口方法
     * @param param
     */
    public void request(String param){
        //转调state来处理
        state.handle(param);
    }

}
