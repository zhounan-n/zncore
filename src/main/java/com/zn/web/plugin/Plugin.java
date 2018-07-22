package com.zn.web.plugin;

public interface Plugin {

    /**
     * 初始化插件
     */
    public void init();

    /**
     * 销毁插件
     */
    public void destroy();

}
