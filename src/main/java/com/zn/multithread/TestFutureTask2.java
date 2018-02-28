package com.zn.multithread;

import java.sql.Connection;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.FutureTask;

/**
 * 同步工具类之FutureTask(获取计算值)
 * Created by zhoun on 2018/2/27.
 **/
public class TestFutureTask2 {

    public static void main(String[] args) {

    }

    private ConcurrentHashMap<String, FutureTask<Connection>> connectionPool = new ConcurrentHashMap<String, FutureTask<Connection>>();
    public Connection getConnection(String key) throws Exception {
        FutureTask<Connection> connectionFutureTask = connectionPool.get(key);
        if (connectionFutureTask != null) {
            return connectionFutureTask.get();
        } else {
            Callable<Connection> callable = new Callable<Connection>() {
                public Connection call() throws Exception {
                    return createConnection();
                }
            };
            FutureTask<Connection> newTask = new FutureTask<Connection>(callable);
            connectionFutureTask = connectionPool.putIfAbsent(key, newTask);
            if (connectionFutureTask == null) {
                connectionFutureTask = newTask;
                connectionFutureTask.run();
            }
            return connectionFutureTask.get();
        }
    }
    private Connection createConnection() {
        return null;
    }

}
