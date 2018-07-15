package com.zn.web.core.impl.suppot;

import java.lang.annotation.Annotation;

/**
 * Created by zhoun on 2018/7/14.
 **/
public abstract class AnnotationClassTemplate extends ClassTemplate {

    protected final Class<? extends Annotation> annotationClass;

    protected AnnotationClassTemplate(String packageName, Class<? extends Annotation> annotationClass) {
        super(packageName);
        this.annotationClass = annotationClass;
    }
}
