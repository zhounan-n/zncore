package com.zn.web.tx;

import com.zn.web.aop.proxy.Proxy;
import com.zn.web.aop.proxy.ProxyChain;
import com.zn.web.tx.annotation.Transcation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * 事务代理
 * Created by zhoun on 2018/7/23.
 **/
public class TransactionProxy implements Proxy {

    private static final Logger logger = LoggerFactory.getLogger(TransactionProxy.class);

    /**
     * 定义一个线程局部变量，用于保存当前线程中是否进行了事务处理，默认为false不处理
     */
    private static final ThreadLocal<Boolean> flagContainer = new ThreadLocal<Boolean>() {
        @Override
        protected Boolean initialValue() {
            return false;
        }
    };

    @Override
    public Object doProxy(ProxyChain proxyChain) throws Throwable {
        Object result = null;
        //判断当前线程是否进行了事务处理
        boolean flag = flagContainer.get();
        //获取目标方法
        Method method = proxyChain.getTargetMethod();
        //若当前线程未进行事务处理，且在目标方法上定义了Transcation注解,则说明该方法需要进行事务处理
        if (!flag && method.isAnnotationPresent(Transcation.class)) {
            //设置当前线程已进行事务处理
            flagContainer.set(true);
            try {
                //开启事务
                
            }catch (Exception e){

            }finally {

            }
        }
        return null;
    }


}
