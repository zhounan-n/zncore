package com.zn.multithread;

import java.util.concurrent.*;

/**
 * semaphore流控
 * Created by zhoun on 2018/3/19.
 **/
public class TestSemaphore {

    private static final int THREAD_COUNT = 30;

    private static ExecutorService threadPool = new ThreadPoolExecutor(10, 20, 5, TimeUnit.SECONDS, new SynchronousQueue<>());

    private static Semaphore s = new Semaphore(10);

    public static void main(String[] args) {
        for (int i = 0; i < THREAD_COUNT; i++) {
            final int num = i;
            threadPool.execute(() -> {
                        try {
                            s.acquire();
                            System.out.println(Thread.currentThread().getName() + "--save data--" + num);
                            s.release();
                        } catch (InterruptedException e) {
                            e.printStackTrace();

                        }

                    }
            );

        }

        threadPool.shutdown();
    }

}
