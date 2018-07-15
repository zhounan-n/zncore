package com.zn.web.core;

import java.lang.annotation.Annotation;
import java.util.List;

/**
 * 类扫描器
 * Created by zhoun on 2018/7/14.
 **/
public interface ClassScanner {

    /**
     * 获取指定包名中的所有类
     */
    List<Class<?>> getClassist(String packageName);

    /**
     * 获取指定包名中指定注解相关的类
     */
    List<Class<?>> getClassListByAnnotation(String packageName, Class<? extends Annotation> annotationClass);

    /**
     * 获取指定包名中指定父类或接口相关的类
     */
    List<Class<?>> getClassListBySuper(String packageNmae, Class<?> superClass);
}
