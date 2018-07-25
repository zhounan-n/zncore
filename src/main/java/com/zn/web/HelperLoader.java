package com.zn.web;

import com.zn.web.aop.AopHelper;
import com.zn.web.dao.DatabaseHelper;
import com.zn.web.ioc.BeanHelper;
import com.zn.web.ioc.IocHelper;
import com.zn.web.mvc.ActionHelper;
import com.zn.web.plugin.PluginHelper;
import com.zn.web.utils.ClassUtil;

/**
 * 加载相应的helper类
 * Created by zhoun on 2018/7/25.
 **/
public final class HelperLoader {


    public static void init() {
        //定义需要加载的Helper类
        Class<?>[] classList = {
                DatabaseHelper.class,
                AopHelper.class,
                ActionHelper.class,
                BeanHelper.class,
                IocHelper.class,
                PluginHelper.class
        };
        //按照顺序加载类
        for (Class<?> cls : classList) {
            ClassUtil.loadClass(cls.getName());
        }
    }


}
