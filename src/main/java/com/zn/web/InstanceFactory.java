package com.zn.web;
import com.zn.web.core.ClassScanner;
import com.zn.web.core.ConfigHelper;
import com.zn.web.core.impl.DefaultClassScanner;
import com.zn.web.utils.ObjectUtil;
import com.zn.web.utils.StringUtil;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 实例工厂
 * Created by zhoun on 2018/7/14.
 **/
public class InstanceFactory {

    /**
     * 用于缓存对应的实例
     */
    private static final Map<String, Object> cache = new ConcurrentHashMap<String, Object>();

    /**
     * classScanner
     */
    private static final String CLASS_SCANNER =  "com.zn.core.class_scanner";

    /**
     * DataSourceFactory
     */
    private static final String DS_FACTORY = "com.zn.core.ds_factory";

    /**
     * DataAccessor
     */
    private static final String DATA_ACCESSOR = "com.zn.core.data_accessor";

    /**
     * HandlerMapping
     */
    private static final String HANDLER_MAPPING = "com.zn.core.handler_mapping";

    /**
     * HandlerInvoker
     */
    private static final String HANDLER_INVOKER = "com.zn.core.handler_invoker";

    /**
     * HandlerExceptionResolver
     */
    private static final String HANDLER_EXCEPTION_RESOLVER = "com.zn.core.handler_exception_resolver";

    /**
     * ViewResolver
     */
    private static final String VIEW_RESOLVER = "com.zn.core.view_resolver";

    /**
     * 获取 ClassScanner
     */
    public static ClassScanner getClassScanner() {
        return getInstance(CLASS_SCANNER, DefaultClassScanner.class);
    }

    @SuppressWarnings("unchecked")
    public static <T> T getInstance(String cacheKey, Class<T> defaultImplClass) {
        // 若缓存中存在对应的实例，则返回该实例
        if (cache.containsKey(cacheKey)) {
            return (T) cache.get(cacheKey);
        }
        // 从配置文件中获取相应的接口实现类配置
        String implClassName = ConfigHelper.getString(cacheKey);
        // 若实现类配置不存在，则使用默认实现类
        if (StringUtil.isEmpty(implClassName)) {
            implClassName = defaultImplClass.getName();
        }
        // 通过反射创建该实现类对应的实例
        T instance = ObjectUtil.newInstance(implClassName);
        // 若该实例不为空，则将其放入缓存
        if (instance != null) {
            cache.put(cacheKey, instance);
        }
        // 返回该实例
        return instance;
    }







}
