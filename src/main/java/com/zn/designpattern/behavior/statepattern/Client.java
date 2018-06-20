package com.zn.designpattern.behavior.statepattern;

/**
 * 状态模式：
 *          对象的行为模式，多态性原则，具体的状态实体类调用
 *          使用场景：投票：
 *                      正常投票  恶意刷票  重复投票  黑名单(不同状态对应不同的处理，状态时对象内部动态的)
 *          状态决定行为，状态实在运行期被改变的，因此行为也随着状态改变而改变
 *          客户端不负责运行期间的状态维护
 * 客户端
 * Created by zhoun on 2018/6/19.
 **/
public class Client {

    public static void main(){
        //创建状态
        State state=new StateA();
        //创建环境
        Context context=new Context();
        //将状态设置到环境中
        context.setStatus(state);
        //请求
        context.request("ss");
    }

}
