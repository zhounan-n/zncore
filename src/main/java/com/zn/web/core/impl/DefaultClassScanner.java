package com.zn.web.core.impl;


import com.zn.web.core.ClassScanner;
import com.zn.web.core.impl.suppot.AnnotationClassTemplate;
import com.zn.web.core.impl.suppot.ClassTemplate;
import com.zn.web.core.impl.suppot.SupperClassTemplate;

import java.lang.annotation.Annotation;
import java.util.List;

/**
 * 默认类扫描器
 * Created by zhoun on 2018/7/14.
 **/
public class DefaultClassScanner implements ClassScanner {

    public List<Class<?>> getClassist(String packageName) {
        return new ClassTemplate(packageName) {
            @Override
            public boolean checkAddClass(Class<?> cls) {
                String className = cls.getName();
                String pkgName = className.substring(0, className.lastIndexOf("."));
                return pkgName.startsWith(packageName);
            }
        }.getClassList();
    }

    public List<Class<?>> getClassListByAnnotation(String packageName, Class<? extends Annotation> annotationClass) {
        return new AnnotationClassTemplate(packageName, annotationClass) {
            @Override
            public boolean checkAddClass(Class<?> cls) {
                return cls.isAnnotationPresent(annotationClass);
            }
        }.getClassList();
    }

    public List<Class<?>> getClassListBySuper(String packageName, Class<?> superClass) {
        return new SupperClassTemplate(packageName, superClass) {
            @Override
            public boolean checkAddClass(Class<?> cls) {
                return superClass.isAssignableFrom(cls) && !superClass.equals(cls);
            }
        }.getClassList();
    }
}
