package com.zn.cache;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 本地缓存实现
 * Created by zhoun on 2019/3/5.
 **/
public class LocalCache {

    /**
     * 默认有效时长 单位:秒
     */
    private static final int DEFAULT_TIMEOUT = 3600;

    private static long SECOND_TIME = 1000;

    private static final Map<String, Object> map;

    private static final Timer timer;

    /**
     * 初始化
     */
    static {
        timer = new Timer();
        map = new ConcurrentHashMap<>();
    }

    private LocalCache() {

    }

    /**
     * 清除缓存任务类
     */
    static class CleanWorkerTask extends TimerTask {

        private String key;

        public CleanWorkerTask(String key) {
            this.key = key;
        }

        @Override
        public void run() {
            LocalCache.remove(key);
        }
    }

    /**
     * 增加缓存
     */
    public static void put(String key, Object value) {
        map.put(key, value);
        timer.schedule(new CleanWorkerTask(key), DEFAULT_TIMEOUT);
    }


    /**
     * 增加缓存
     */
    public static void put(String key, Object value, int timeout) {
        map.put(key, value);
        timer.schedule(new CleanWorkerTask(key), timeout * SECOND_TIME);
    }

    /**
     * 增加缓存
     */
    public static void put(String key, Object value, Date expireTime) {
        map.put(key, value);
        timer.schedule(new CleanWorkerTask(key), expireTime);
    }

    /**
     * 批量增加缓存
     */
    public static void putAll(Map<String, Object> m) {
        map.putAll(m);
        for (String key : m.keySet()) {
            timer.schedule(new CleanWorkerTask(key), DEFAULT_TIMEOUT);
        }
    }

    /**
     * 批量增加缓存
     *
     * @param m
     */
    public static void putAll(Map<String, Object> m, int timeout) {
        map.putAll(m);

        for (String key : m.keySet()) {
            timer.schedule(new CleanWorkerTask(key), timeout * SECOND_TIME);
        }
    }

    /**
     * 批量增加缓存
     *
     */
    public static void putAll(Map<String, Object> m, Date expireTime) {
        map.putAll(m);

        for (String key : m.keySet()) {
            timer.schedule(new CleanWorkerTask(key), expireTime);
        }
    }


    /**
     * 获取缓存
     *
     * @param key
     * @return
     */
    public static Object get(String key) {
        return map.get(key);
    }

    /**
     * 查询缓存是否包含key
     *
     * @param key
     * @return
     */
    public static boolean containsKey(String key) {
        return map.containsKey(key);
    }

    /**
     * 删除缓存
     *
     * @param key
     */
    public static void remove(String key) {
        map.remove(key);
    }

    /**
     * 删除缓存
     *
     * @param o
     */
    public static void remove(Object o) {
        map.remove(o);
    }


    /**
     * 返回缓存大小
     *
     * @return
     */
    public static int size() {
        return map.size();
    }

    /**
     * 清除所有缓存
     *
     * @return
     */
    public static void clear() {
        if (size() > 0) {
            map.clear();
        }
        timer.cancel();
    }
}
