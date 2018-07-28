package com.zn.web.aop;

import com.zn.web.FrameworkConstant;
import com.zn.web.InstanceFactory;
import com.zn.web.annotation.Service;
import com.zn.web.aop.annotation.Aspect;
import com.zn.web.aop.annotation.AspectOrder;
import com.zn.web.aop.proxy.Proxy;
import com.zn.web.aop.proxy.ProxyManager;
import com.zn.web.core.ClassHelper;
import com.zn.web.core.ClassScanner;
import com.zn.web.ioc.BeanHelper;
import com.zn.web.plugin.Plugin;
import com.zn.web.plugin.PluginProxy;
import com.zn.web.tx.TransactionProxy;
import com.zn.web.utils.ClassUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.lang.annotation.Annotation;
import java.util.*;


/**
 * 初始化aop框架
 * Created by zhoun on 2018/7/22.
 **/
public class AopHelper {

    /**
     * 获取ClassScanner
     */
    private static final ClassScanner classScanner = InstanceFactory.getClassScanner();

    static {
        try {
            //创建proxy map用于存放代理类和目标类的映射关系
            Map<Class<?>, List<Class<?>>> proxyMap = createProxyMap();
            //创建TargetMap (用于存放目标类和代理类列表的映射)
            Map<Class<?>, List<Proxy>> targetMap = createTargetMap(proxyMap);
            //遍历TargetMap
            for (Map.Entry<Class<?>, List<Proxy>> targetEntry : targetMap.entrySet()) {
                //分别获取map中的key,value
                Class<?> targetClass = targetEntry.getKey();
                List<Proxy> proxyList = targetEntry.getValue();

                //创建代理实例
                Object proxyInstance = ProxyManager.createProxy(targetClass, proxyList);
                //用代理实例覆盖目标实例，并放入bean容器
                BeanHelper.setBean(targetClass, proxyInstance);
            }

        } catch (Exception e) {
            throw new com.zn.web.core.fault.InitializationError("aop出错", e);
        }
    }

    private static Map<Class<?>, List<Class<?>>> createProxyMap() throws Exception {
        Map<Class<?>, List<Class<?>>> proxyMap = new LinkedHashMap<>();
        //添加相关代理
        //插件代理
        addPluginProxy(proxyMap);
        //切面代理
        addAspectProxy(proxyMap);
        //事务代理
        addTranscationProxy(proxyMap);
        return proxyMap;
    }

    private static void addPluginProxy(Map<Class<?>, List<Class<?>>> proxyMap) throws Exception {
        //获取插件包名下的父类为PluginProxy的所有类(插件代理类)
        List<Class<?>> pluginProxyList = classScanner.getClassListBySuper(FrameworkConstant.PLUGIN_PACKAGE, Plugin.class);
        if (CollectionUtils.isNotEmpty(pluginProxyList)) {
            //遍历所有插件代理类
            for (Class<?> pluginProxyClass : pluginProxyList) {
                //遍历所有插件代理类
                PluginProxy pluginProxy = (PluginProxy) pluginProxyClass.newInstance();
                //将插件代理类及其目标类放入Proxy Map中
                proxyMap.put(pluginProxyClass, pluginProxy.getTragetClassList());
            }
        }
    }

    private static void addAspectProxy(Map<Class<?>, List<Class<?>>> proxyMap) throws Exception {
        //获取切面类
        List<Class<?>> aspectProxyClasslist = ClassHelper.getClassListBySuper(AspectProxy.class);
        //添加插件包下所有的切面类
        aspectProxyClasslist.addAll(classScanner.getClassListBySuper(FrameworkConstant.PLUGIN_PACKAGE, AspectProxy.class));
        //排序切面类
        sortAspectProxyClasslist(aspectProxyClasslist);
        //遍历切面类
        for (Class<?> aspectProxyClass : aspectProxyClasslist) {
            //判断Aspect注解是否存在
            if (aspectProxyClass.isAnnotationPresent(Aspect.class)) {
                //hu获取aspect注解
                Aspect aspect = aspectProxyClass.getAnnotation(Aspect.class);
                //创建目标类列表
                List<Class<?>> targetClassList = createTargetClassList(aspect);
                //初始化Proxy Map
                proxyMap.put(aspectProxyClass, targetClassList);
            }
        }

    }

    private static List<Class<?>> createTargetClassList(Aspect aspect) throws Exception {
        //TODO
        List<Class<?>> targetClasslist = new ArrayList<>();
        //获取Aspect注解相关的属性
        String pkg = aspect.pkg();
        String cls = aspect.cls();
        Class<? extends Annotation> annotation = aspect.annottion();
        //若包名不为空，则需要进一步判断类名是否为空
        if (StringUtils.isNoneEmpty(pkg)) {
            if (StringUtils.isNoneEmpty(cls)) {
                //若类名为空，则仅添加该类
                targetClasslist.add(ClassUtil.loadClass(pkg + "." + cls, false));
            } else {
                //若注解不为空且不是Aspect注解，则添加指定包名下带有该注解的所有类
                if (annotation != null && !annotation.equals(Aspect.class)) {
                    targetClasslist.addAll(classScanner.getClassListByAnnotation(pkg, annotation));
                } else {
                    //否则添加该包名下所有类
                    targetClasslist.addAll(classScanner.getClassist(pkg));
                }
            }
        } else {
            //若注解不为空且Aspect注解，则添加该应用包下带有该注解的所有类
            if (annotation != null && !annotation.equals(Aspect.class)) {
                targetClasslist.addAll(ClassHelper.getClassByAnnotation(annotation));
            }
        }
        return targetClasslist;
    }

    private static void addTranscationProxy(Map<Class<?>, List<Class<?>>> proxyMap) {
        //使用tRanscationProxy代理所有service类
        List<Class<?>> serviceClasslist = ClassHelper.getClassByAnnotation(Service.class);
        proxyMap.put(TransactionProxy.class, serviceClasslist);
    }

    private static void sortAspectProxyClasslist(List<Class<?>> proxyClassList) {
        //排序代理类列表
        Collections.sort(proxyClassList, (Class<?> aspect1, Class<?> aspect2) -> {
            if (aspect1.isAnnotationPresent(AspectOrder.class) || aspect2.isAnnotationPresent(AspectOrder.class)) {
                //若有Order注解，则优先比较(序号的值越小越靠前)
                if (aspect1.isAnnotationPresent(AspectOrder.class)) {
                    return getOrderValue(aspect1) - getOrderValue(aspect2);
                } else {
                    return getOrderValue(aspect2) - getOrderValue(aspect1);
                }
            } else {
                //若无order注解 则按类名(字母顺序)
                return aspect1.hashCode() - aspect2.hashCode();
            }

        });
    }


    private static int getOrderValue(Class<?> aspect) {
        return aspect.getAnnotation(AspectOrder.class) != null ? aspect.getAnnotation(AspectOrder.class).value() : 0;
    }

    private static Map<Class<?>, List<Proxy>> createTargetMap(Map<Class<?>, List<Class<?>>> proxyMap) throws Exception {
        Map<Class<?>, List<Proxy>> targetMap = new HashMap<>();
        //遍历proxyMap
        for (Map.Entry<Class<?>, List<Class<?>>> proxyEntry : proxyMap.entrySet()) {
            //分别获取map中的key value
            Class<?> proxyClass = proxyEntry.getKey();
            List<Class<?>> targetClassList = proxyEntry.getValue();
            //遍历目标类列表
            for (Class<?> targetClass : targetClassList) {
                //创建代理类(切面类)实例
                Proxy baseAspect = (Proxy) proxyClass.newInstance();
                //初始化targetMap
                if (targetMap.containsKey(targetClass)) {
                    targetMap.get(targetClass).add(baseAspect);
                } else {
                    List<Proxy> baseAspectList = new ArrayList<Proxy>();
                    baseAspectList.add(baseAspect);
                    targetMap.put(targetClass, baseAspectList);
                }
            }
        }
        return targetMap;
    }
}
