package com.zn.RateLimit;

import com.google.common.util.concurrent.RateLimiter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * guava包 限流
 * Created by zhoun on 2018/6/26.
 **/
public class Test1 {

    public static void main(String[] args) {
        //新建一个每秒限制3个的令牌桶
        RateLimiter rateLimiter = RateLimiter.create(3.0);
        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(100);
        for (int i = 0; i < 100; i++) {
            threadPoolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    //获取令牌桶中的一个令牌 最多等待10秒
                    if (rateLimiter.tryAcquire(1, 10, TimeUnit.SECONDS)) {
                        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                    }
                }
            });
        }
        threadPoolExecutor.shutdown();

    }

}
