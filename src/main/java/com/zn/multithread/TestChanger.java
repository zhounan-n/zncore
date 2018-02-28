package com.zn.multithread;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 在两个线程之间传输数据
 * Created by zhoun on 2018/2/27.
 **/
public class TestChanger {

    private static ExecutorService threadPool = Executors.newFixedThreadPool(2);
    private static final Exchanger<String> exgr = new Exchanger<String>();

    public static void main(String[] args) {

        threadPool.execute(new Runnable() {
            public void run() {
                try {
                    String A = "银行流水A";// A录入银行流水数据
                    exgr.exchange(A);
                } catch (InterruptedException e) {
                }
            }
        });

        threadPool.execute(new Runnable() {
            public void run() {
                try {
                    String B = "银行流水B";// B录入银行流水数据
                    String A = exgr.exchange(B);
                    System.out.println("A和B数据是否一致：" + A.equals(B) + ",A录入的是："
                            + A + ",B录入是：" + B);
                } catch (InterruptedException e) {
                }
            }
        });
        threadPool.shutdown();

        Exchanger exchanger = new Exchanger();
        Car car = new Car(exchanger);
        Bike bike = new Bike(exchanger);
        car.start();
        bike.start();
        System.out.println("MAIN END!");

    }

    static class Car extends Thread {
        private Exchanger<String> exchanger;

        public Car(Exchanger<String> exchanger) {
            super();
            this.exchanger = exchanger;
        }


        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName() + ": " + exchanger.exchange("Car"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    static class Bike extends Thread {
        private Exchanger<String> exchanger;

        public Bike(Exchanger<String> exchanger) {
            this.exchanger = exchanger;
        }

        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName() + ": " + exchanger.exchange("Bike"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
