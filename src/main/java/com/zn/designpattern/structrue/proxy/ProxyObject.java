package com.zn.designpattern.structrue.proxy;

/**
 * 代理对象角色
 * Created by zhoun on 2018/7/5.
 **/
public class ProxyObject extends AbstractObject {

    /**
     * 持有被代理对象
     */
    RealObject realObject = new RealObject();

    @Override
    public void operation() {
        //调用目标对象之间可以做相关操作
        System.out.println("before");
        realObject.operation();
        System.out.println("after");
    }

}
