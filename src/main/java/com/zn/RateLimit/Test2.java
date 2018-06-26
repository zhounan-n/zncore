package com.zn.RateLimit;

import com.google.common.util.concurrent.RateLimiter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 限流
 * Created by zhoun on 2018/6/26.
 **/
public class Test2 {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        //速率是每秒只有5个许可
        final RateLimiter rateLimiter = RateLimiter.create(5.0);
        for (int i = 0; i < 10; i++) {
            final int n = i;
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        //获取许可
                        rateLimiter.acquire();
                        System.out.println("Accessing:" + n + ",time:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            executorService.execute(runnable);

        }
        executorService.shutdown();
    }

}
