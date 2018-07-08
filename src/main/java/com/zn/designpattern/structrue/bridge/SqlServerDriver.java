package com.zn.designpattern.structrue.bridge;

/**
 * Created by zhoun on 2018/7/8.
 **/
public class SqlServerDriver implements Driver {

    @Override
    public void connect() {
        System.out.println("连接sqlserver数据库");
    }
}
