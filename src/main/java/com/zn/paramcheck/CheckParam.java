package com.zn.paramcheck;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface CheckParam {

    /**
     * 请求当前接口所需要的参数，多个以小写的逗号分开
     *
     * @return
     */
    String fieldNames() default "";

    /**
     * 传递参数的对象类型
     */
    Class<?> parameter() default Object.class;

    /**
     * 默认不校验参数
     */
    boolean require() default false;

}
