package com.zn.web.plugin;

import com.zn.web.mvc.DataContext;

import javax.servlet.ServletContext;

/**
 * 基于Web的插件抽象实现，拥有plugin接口的所有类
 * 可在子类注册Servlet Filter Listener等
 * Created by zhoun on 2018/7/22.
 **/
public abstract class WebPlugin implements Plugin {

    @Override
    public void init() {

    }

    @Override
    public void destroy() {

    }

    public abstract void register(ServletContext servletContext);
}
