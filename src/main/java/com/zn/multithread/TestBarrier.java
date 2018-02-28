package com.zn.multithread;

import java.util.Random;
import java.util.concurrent.*;

/**
 * 栅栏(线程间互相等待)
 * Created by zhoun on 2018/2/27.
 **/
public class TestBarrier {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        final CyclicBarrier barrier = new CyclicBarrier(5, new Runnable() {
            public void run() {
                System.out.println("所有玩家都已进入游戏");
            }
        });
        for (int i = 0; i < 5; i++) {
            //executorService.submit();
            executorService.execute(new Player("玩家" + i, barrier));
        }
        executorService.shutdown();
    }

    private static class Player implements Runnable {
        final String name;
        final CyclicBarrier barrier;

        public Player(String name, CyclicBarrier barrier) {
            this.name = name;
            this.barrier = barrier;
        }

        public void run() {
            try {
                TimeUnit.SECONDS.sleep(1 + (new Random().nextInt(3)));
                System.out.println(name + "已准备，等待其他玩家……");
                barrier.await();
                TimeUnit.SECONDS.sleep(1 + (new Random().nextInt(3)));
                System.out.println(name + "玩家已加入游戏");
            } catch (InterruptedException e) {
                System.out.println(name + "离开游戏");
            } catch (BrokenBarrierException e2) {
                System.out.println(name + "离开游戏");
                e2.printStackTrace();
            }
        }
    }

}
