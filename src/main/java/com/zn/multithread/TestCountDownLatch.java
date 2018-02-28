package com.zn.multithread;

import java.util.concurrent.CountDownLatch;

/**
 * 同步工具类之闭锁CountDownLatch
 * (等待某一事件的发生)
 * Created by zhoun on 2018/2/26.
 **/
public class TestCountDownLatch {

    public static void main(String[] args) {

        int threadCount = 10;
        final CountDownLatch countDownLatch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            new Thread(new Runnable() {
                public void run() {
                    System.out.println("线程" + Thread.currentThread().getId() + "开始启动");
                    try {

                        Thread.sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println("线程" + Thread.currentThread() + "已到达终点");
                    countDownLatch.countDown();
                }
            }).start();
        }

        try {
            countDownLatch.await();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("10个线程已计算完毕，开始计算排名");
    }

}
