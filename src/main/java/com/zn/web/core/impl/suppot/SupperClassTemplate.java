package com.zn.web.core.impl.suppot;

/**
 * Created by zhoun on 2018/7/14.
 **/
public abstract class SupperClassTemplate extends ClassTemplate{

    protected final Class<?> superClass;

    protected SupperClassTemplate(String packageName, Class<?> superClass) {
        super(packageName);
        this.superClass = superClass;
    }
}
