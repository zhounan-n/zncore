package com.zn.web.plugin;

import com.zn.web.FrameworkConstant;
import com.zn.web.InstanceFactory;
import com.zn.web.core.ClassScanner;
import com.zn.web.core.fault.InitializationError;

import java.util.ArrayList;
import java.util.List;

/**
 * 初始化插件
 * Created by zhoun on 2018/7/22.
 **/
public class PluginHelper {

    /**
     * 创建一个插件列表 用于存放插件
     */
    private static final List<Plugin> pluginList = new ArrayList<>();

    /**
     * 获取classScanner
     */
    private static final ClassScanner classScanner = InstanceFactory.getClassScanner();

    static {
        try {
            //获取遍历所有的插件类
            List<Class<?>> pluginClassList = classScanner.getClassListBySuper(FrameworkConstant.PLUGIN_PACKAGE, Plugin.class);
            for (Class<?> pluginClass : pluginClassList) {
                //创建插件实例
                Plugin plugin = (Plugin) pluginClass.newInstance();
                //调用初始方法
                plugin.init();
                //将插件实例添加到插件列表中
                pluginList.add(plugin);
            }
        } catch (Exception e) {
            throw new InitializationError("初始化pluginHelper出错");
        }
    }

    /**
     * 获取所有的插件
     */
    public static List<Plugin> getPluginList() {
        return pluginList;
    }

}
