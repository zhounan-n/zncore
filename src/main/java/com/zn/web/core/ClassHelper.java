package com.zn.web.core;



import com.zn.web.InstanceFactory;

import java.lang.annotation.Annotation;
import java.util.List;

/**
 * 类操作助手类
 * Created by zhoun on 2018/7/15.
 **/
public final class ClassHelper {


    /**
     * 获取基础包名
     */
    private static final String basePackage = ConfigHelper.getString("smart.framework.app.base_package");

    /**
     * 获取ClassScanner
     */
    private static final ClassScanner classScanner = InstanceFactory.getClassScanner();

    /**
     * 获取基础包中的所有类
     */
    public static List<Class<?>> getClassList() {
        return classScanner.getClassist(basePackage);
    }

    /**
     * 获取指定包中基础父类或接口相关类
     */
    public static List<Class<?>> getClassListBySuper(Class<?> superClass) {
        return classScanner.getClassListBySuper(basePackage, superClass);
    }

    /**
     * 获取基础包名中指定注解相关的类
     */
    public static List<Class<?>> getClassByAnnotation(Class<? extends Annotation> annotatioClass) {
        return classScanner.getClassListByAnnotation(basePackage, annotatioClass);
    }


}
