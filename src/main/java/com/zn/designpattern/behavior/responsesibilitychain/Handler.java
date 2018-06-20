package com.zn.designpattern.behavior.responsesibilitychain;

/**
 * 抽象处理者角色类
 * Created by zhoun on 2018/6/20.
 **/
public abstract class Handler {

    /**
     * 持有下一个处理请求的对象
     */
    protected Handler successor = null;

    public Handler getSuccessor() {
        return successor;
    }

    /**
     * 设置下一个处理请求的对象
     *
     * @param handler
     */
    public void setSuccessor(Handler handler) {
        this.successor = successor;
    }


    /**
     * 处理聚餐费用的请求
     *
     * @param user
     * @param fee
     * @return
     */
    public abstract String handleRequest(String user, double fee);

}
