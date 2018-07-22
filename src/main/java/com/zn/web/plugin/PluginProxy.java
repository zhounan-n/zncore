package com.zn.web.plugin;

import com.zn.web.aop.proxy.Proxy;

import java.util.List;

/**
 * 插件代理
 * Created by zhoun on 2018/7/22.
 **/
public abstract class PluginProxy implements Proxy {

    public abstract List<Class<?>> getTragetClassList();
}
