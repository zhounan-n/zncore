package com.zn.web.ioc;

import com.zn.core.ClassHelper;
import com.zn.core.fault.InitializationError;
import com.zn.ioc.annotation.Impl;
import com.zn.ioc.annotation.Inject;
import com.zn.utils.ArrayUtil;
import org.apache.commons.collections4.CollectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

/**
 * 初始化IOC容器
 * (控制反转 DI依赖注入)通过反射获取所有的成员变量，循环这些变量然后在循环中判断当前成员变量是否带有@Inject注解
 * 有该注解则获得该bean实例，利用setField方法修改成员变量的值
 * Created by zhoun on 2018/7/15.
 **/
public final class IocHelper {

    static {
        try {
            //获取并遍历所有的bean类
            Map<Class<?>, Object> beanMap = BeanHelper.getBeanMap();
            for (Map.Entry<Class<?>, Object> entry : beanMap.entrySet()) {
                //获取bean类和bean实例
                Class<?> beanClass = entry.getKey();
                Object instance = entry.getValue();
                //获取bean类中所有的字段
                Field[] fields = beanClass.getDeclaredFields();
                if (ArrayUtil.isNotEmpty(fields)) {
                    //遍历所有的Bean字段
                    for (Field field : fields) {
                        //判断当前bean字段是否有Inject注解
                        if (field.isAnnotationPresent(Inject.class)) {
                            //获取bean字段对应的接口
                            Class<?> intefaceClass = field.getType();
                            //获取Bean字段对应的实现类
                            Class<?> implementClass = findImplementClass(intefaceClass);

                            //若存在实现类 则执行以下代码
                            if (implementClass != null) {
                                //从bean map中获取该实现类对应的实现类实例
                                Object implementInstance = beanMap.get(implementClass);
                                //设置该bean字段的值
                                if (implementInstance != null) {
                                    //将字段设置为public
                                    field.setAccessible(true);
                                    //设置字段初始值
                                    field.set(instance, implementInstance);
                                } else {
                                    throw new InitializationError("依赖注入失败，类名:" + beanClass.getSimpleName() + "接口名：" + intefaceClass.getSimpleName());
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            throw new InitializationError("初始化IOCHelper出错", e);
        }
    }

    public static Class<?> findImplementClass(Class<?> interfacClass) {
        Class<?> implementClass = interfacClass;
        //判断接口是否标注了Impl
        if (interfacClass.isAnnotationPresent(Impl.class)) {
            //获取强指定的实现类
            implementClass = interfacClass.getAnnotation(Impl.class).value();

        } else {
            //获取该接口所有的实现类
            List<Class<?>> implementClassList = ClassHelper.getClassListBySuper(interfacClass);
            if (CollectionUtils.isNotEmpty(implementClassList)) {
                implementClass = implementClassList.get(0);
            }

        }
        //返回实现类对象
        return implementClass;
    }

}
