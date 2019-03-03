/*
package com.zn.multithread;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.BlockingQueue;

*/
/**
 * Created by zhoun on 2018/8/30.
 **//*

public class ThreadExecutor {

    private volatile boolean RUNNING = true;

    */
/**
     * 所有任务都放队列中 让线程池来消费
     *//*

    private static BlockingQueue<Runnable> queue = null;

    private final HashSet<Worker> workers = new HashSet<>();

    private List<Thread> threadList = new ArrayList<>();

    */
/**
     * 工作线程数
     *//*

    int poolSize = 0;
    */
/**
     * 核心线程数(创建了多少个工作线程)
     *//*

    int coreSize = 0;

    boolean shutdown = false;

    public ThreadExecutor(int poolSize) {
        this.poolSize = poolSize;
    }




}
*/
