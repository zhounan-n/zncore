package com.zn.web.core;

import com.zn.constant.ConfigConstant;
import com.zn.utils.PropsUtil;

import java.util.Map;
import java.util.Properties;

/**
 * 读取配置文件
 * Created by zhoun on 2018/7/14.
 **/
public final class ConfigHelper {

    private static final Properties configProps = PropsUtil.loadProps(ConfigConstant.CONFIG_FILE);

    /**
     * 获取jdbc驱动
     */
    public static String getJdbcDriver(String key) {
        return PropsUtil.getString(configProps, key);
    }


    /**
     * 获取 String 类型的属性值
     */
    public static String getString(String key) {
        return PropsUtil.getString(configProps, key);
    }

    /**
     * 获取 String 类型的属性值（可指定默认值）
     */
    public static String getString(String key, String defaultValue) {
        return PropsUtil.getString(configProps, key, defaultValue);
    }

    /**
     * 获取 int 类型的属性值
     */
    public static int getInt(String key) {
        return PropsUtil.getNumber(configProps, key);
    }

    /**
     * 获取 int 类型的属性值（可指定默认值）
     */
    public static int getInt(String key, int defaultValue) {
        return PropsUtil.getNumber(configProps, key, defaultValue);
    }

    /**
     * 获取 boolean 类型的属性值
     */
    public static boolean getBoolean(String key) {
        return PropsUtil.getBoolean(configProps, key);
    }

    /**
     * 获取 int 类型的属性值（可指定默认值）
     */
    public static boolean getBoolean(String key, boolean defaultValue) {
        return PropsUtil.getBoolean(configProps, key, defaultValue);
    }

    /**
     * 获取指定前缀的相关属性
     *
     * @since 2.2
     */
    public static Map<String, Object> getMap(String prefix) {
        return PropsUtil.getMap(configProps, prefix);
    }

/*
    public static String getAppBasePackage() {
        return PropsUtil.getString(configProps, ConfigConstant.APP_BASE_PACKAGE);
    }*/
}
