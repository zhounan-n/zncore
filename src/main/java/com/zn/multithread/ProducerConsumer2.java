package com.zn.multithread;

/**
 * wait notify实现
 * Created by zhoun on 2019/3/3.
 **/
public class ProducerConsumer2 {

    private static Integer count = 0;
    private Integer FULL = 10;
    public static String LOCK = "lock";


    class Producer implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (LOCK) {
                    while (count.equals(FULL)) {
                        try {
                            LOCK.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    count++;
                    System.out.println(Thread.currentThread() + "生产者生产,目前共有" + count);
                    LOCK.notifyAll();
                }
            }
        }
    }

    class Consumer implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (LOCK) {
                    while (count.equals(0)) {
                        try {
                            LOCK.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    count--;
                    System.out.println(Thread.currentThread() + "消费者消费,目前共有" + count);
                    LOCK.notifyAll();
                }
            }
        }
    }
    public static void main(String[] args) {
        ProducerConsumer2 producerConsumer2 = new ProducerConsumer2();
        new Thread(producerConsumer2.new Producer()).start();
        new Thread(producerConsumer2.new Consumer()).start();
        new Thread(producerConsumer2.new Producer()).start();
        new Thread(producerConsumer2.new Consumer()).start();
        new Thread(producerConsumer2.new Producer()).start();
        new Thread(producerConsumer2.new Consumer()).start();
    }

}
