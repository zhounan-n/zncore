package com.zn.web.ioc;


import com.zn.web.annotation.Service;
import com.zn.web.aop.annotation.Aspect;
import com.zn.web.core.ClassHelper;
import com.zn.web.core.fault.InitializationError;
import com.zn.web.ioc.annotation.Bean;
import com.zn.web.mvc.annotation.Action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 初始化相关bean类(相当于实现了一个bean容器)
 * Created by zhoun on 2018/7/15.
 **/
public class BeanHelper {

    /**
     * Bean Map (bean类 ==》bean实例)
     */
    private static final Map<Class<?>, Object> beanMap = new HashMap<Class<?>, Object>();

    static {
        try {
            //获取应用包路径下的所有类
            List<Class<?>> classList = ClassHelper.getClassList();
            for (Class<?> cls : classList) {
                //处理带有bean service action aspect注解的类
                if (cls.isAnnotationPresent(Bean.class) ||
                        cls.isAnnotationPresent(Service.class) ||
                        cls.isAnnotationPresent(Action.class) ||
                        cls.isAnnotationPresent(Aspect.class)) {

                    //创建bean实例
                    Object instance = cls.newInstance();
                    //把bean实例放入bean map中
                    beanMap.put(cls, instance);

                }
            }
        } catch (Exception e) {
            throw new InitializationError("初始化BeanHelper出错", e);
        }
    }

    public static Map<Class<?>, Object> getBeanMap() {
        return beanMap;
    }

    /**
     * 获取bean实例
     */
    public static <T> T getBean(Class<T> cls) {
        if (!beanMap.containsKey(cls)) {
            throw new RuntimeException("无法根据类名获取实例");
        }
        return (T) beanMap.get(cls);
    }

    /**
     * 设置bean实例
     */
    public static void setBeanMap(Class<?> tClass, Object o) {
        beanMap.put(tClass, o);
    }


    /**
     * 设置 Bean 实例
     */
    public static void setBean(Class<?> cls, Object obj) {
        beanMap.put(cls, obj);
    }

}
