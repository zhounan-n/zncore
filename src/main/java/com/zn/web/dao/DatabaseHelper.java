package com.zn.web.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;

/**
 * 封装数据库相关工作
 * Created by zhoun on 2018/7/23.
 **/
public class DatabaseHelper {

    private static final Logger logger = LoggerFactory.getLogger(DatabaseHelper.class);

    /**
     * 定义一个局部线程变量(使每个线程都有自己的连接)
     */
    private static final ThreadLocal<Connection> connContainer=new ThreadLocal<>();

    /**
     * 获取数据源工厂
     */
    //private static final DataSourceFactory

}
