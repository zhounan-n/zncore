package com.zn.designpattern;

/**
 * 单例模式懒汉模式(调用的时候再去生成实例，空间换时间)
 * Created by zhoun on 2018/6/19.
 **/
public class Singleton2 {

    private static Singleton2 singleton2;

    /**
     * volatile修饰表示共享内存 不会被本地线程缓存
     */
    private static volatile Singleton2 instance;

    private Singleton2() {
    }

    public static Singleton2 getInstance() {
        if (singleton2 == null) {
            singleton2 = new Singleton2();
        }
        return singleton2;
    }


    /**
     * 线程安全 会降低访问速度 而且每次都要判断
     */
    public static synchronized Singleton2 getSingleton2Instance() {
        if (singleton2 == null) {
            singleton2 = new Singleton2();
        }
        return singleton2;
    }

    /**
     * 使用双重检查锁实现，既可以实现线程安全，又可以不用太浪费性能
     */
    public static Singleton2 getInstance2() {
        //先检查实例是否存在，存在才进入同步快
        if (instance == null) {
            synchronized (Singleton2.class) {
                //再次检查对象是否存在，不存在才创建对象
                if (instance == null) {
                    instance = new Singleton2();
                }
            }
        }
        return instance;
    }

}
