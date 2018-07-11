package com.zn.ratelimit;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 限流
 * Created by zhoun on 2018/6/26.
 **/
public class Test3 {

    /**
     * 接口服务的限制速率
     */
    private static final ConcurrentHashMap<String, Double> resourceMap = new ConcurrentHashMap<>();
    /**
     * 限制用户对接口的访问速率
     */
    private static final ConcurrentHashMap<String, RateLimiter> userResourceLimitMap =new ConcurrentHashMap<>();

    static {
        resourceMap.putIfAbsent("methodA", 10.0);
    }


    public static void updateResourceQps(String resoure, double qps) {
        resourceMap.putIfAbsent(resoure, qps);
    }

    public void removeResource(String resource) {
        resourceMap.remove(resource);
    }

    public static int enter(String resource, String userKey) {
        long t1 = System.currentTimeMillis();
        Double qps = resourceMap.get(resource);
        //不限流
        if (qps == null || qps == 0.0) {
            return 0;
        }
        String key = resource + userKey;
        RateLimiter rateLimiter = userResourceLimitMap.get(key);
        if (rateLimiter == null) {
            rateLimiter = RateLimiter.create(qps);
            RateLimiter putByOtherLimit = userResourceLimitMap.putIfAbsent(key, rateLimiter);
            if (putByOtherLimit != null) {
                rateLimiter = putByOtherLimit;
            }
            rateLimiter.setRate(qps);
        }
        //非阻塞
        if (!rateLimiter.tryAcquire()) {
            //限速中 提示用户
            System.out.println("use:" + (System.currentTimeMillis() - t1) + "ms;" + resource + "visit too frequently by key" + userKey);
            return 99;
        } else {
            //正常访问
            System.out.println("use :" + (System.currentTimeMillis() - t1) + "ms ; ");
            return 0;
        }


    }

    public static void main(String[] args) throws InterruptedException{
        Test3.updateResourceQps("methodB", 5.0);
        testB();
    }

    public static void testA() throws InterruptedException {
        int i = 0;
        while (true) {
            i++;
            long t2 = System.currentTimeMillis();
            System.out.println(" begin:" + t2 + " , hanchao:" + i);
            int result = Test3.enter("methodA", "hanchao");
            if (result == 99) {
                i = 0;
                Thread.sleep(1000);
            }
        }
    }

    public static void testB() throws InterruptedException {
        //测试other
        int y = 0;
        while (true) {
            y++;
            long t3 = System.currentTimeMillis();
            System.out.println(" begin:" + t3 + " , tom:" + y);

            int result2 = Test3.enter("methodB", "tom");
            if (result2 == 99) {
                y = 0;
                Thread.sleep(1000);
            }
        }
    }

}
