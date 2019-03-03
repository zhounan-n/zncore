package com.zn.multithread;

import java.util.concurrent.*;

/**
 * 模拟生产消费者模型
 * Created by zhoun on 2019/3/3.
 **/
public class ProducerConsumer {

    public static void main(String[] args) {
        ProducerConsumer pc = new ProducerConsumer();
        Storage s = pc.new Storage();

        ExecutorService executorService = Executors.newCachedThreadPool();
        Producer p = pc.new Producer("张三", s);
        Producer p2 = pc.new Producer("李四", s);

        Consumer c = pc.new Consumer("王五", s);
        Consumer c2 = pc.new Consumer("王柳", s);

        executorService.submit(p);
        executorService.submit(c);
        executorService.submit(c2);
    }

    /**
     * 生产者
     */
    public class Producer implements Runnable {

        private String name;
        private Storage storage = null;

        public Producer(String name, Storage storage) {
            this.name = name;
            this.storage = storage;
        }


        @Override
        public void run() {
            try {
                while (true) {
                    Product product = new Product((int) (Math.random() * 10000));
                    System.out.println(name + "准备生产(" + product.toString() + ").");
                    storage.push(product);
                    System.out.println(name + "已生产(" + product.toString() + ").");
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 消费者
     */
    public class Consumer implements Runnable {
        private String name;
        private Storage storage = null;

        public Consumer(String name, Storage storage) {
            this.name = name;
            this.storage = storage;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    System.out.println(name + "准备消费产品");
                    Product product = storage.pop();
                    System.out.println("------------------------");
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }


    /**
     * 仓库  用来存放产品
     */
    public class Storage {

        BlockingQueue<Product> queue = new LinkedBlockingQueue<>(10);

        /**
         * 生产
         */
        public void push(Product product) {
            queue.add(product);
        }

        /**
         * 消费
         */
        public Product pop() throws InterruptedException {
            return queue.take();
        }
    }

    /**
     * 产品
     */
    public class Product {
        private int id;

        public Product(int id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return "产品:" + this.id;
        }

    }

}
