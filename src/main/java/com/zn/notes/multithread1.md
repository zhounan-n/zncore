---
layout: posts
title: java多线程之线程同步工具类总结
date: 2018-03-19 21:09:46
tags: 多线程
categories: java基础
---

------

一本技术书看完不总结一下，总觉得没有把知识转换成自己的，所以总结是非常有必要的，忘了还可以回过头来看一看。

以下内容基本总结于java并发编程实战这本书，下面开始总结几种同步工具类。

> * 闭锁
> * 信号量
> * 栅栏


## 闭锁

闭锁可以用来确保一些活动等待其他活动执行完成后再执行

######  **CountDownLatch是一种灵活的闭锁实现。它可以使一个或多个线程等待一组时间发生。await会一直阻塞到计数器为0。ountDown减少计数，相当于一个开关**
<!--more-->
代码示例

    int threadCount = 10;
    final CountDownLatch countDownLatch = new CountDownLatch(threadCount);
    for (int i = 0; i < threadCount; i++) {
        new Thread(new Runnable() {
            @Override
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

######  **FutureTask也可以做闭锁。(执行任务完毕后获取结果)仅在计算完成时才能获取结果，如果计算尚未完成则阻塞get方法，一旦计算完成就不能重新开始或取消计算**

FutureTak使用场景：（此处参考http://www.importnew.com/25286.html）

1：利用FutureTask和ExecutorService,可以用多线程额方式提交计算任务，主线程继续执行其他任务，当主线程需要子线程的计算结果时再获取子线程的计算结果。

    public static void main(String[] args) {
    TestFutureTask inst = new TestFutureTask();
    //创建任务集合
    List<FutureTask<Integer>> taskList = new ArrayList<FutureTask<Integer>>();
    //创建线程池
    ExecutorService executorService = Executors.newFixedThreadPool(5);
    for (int i = 0; i < 10; i++) {
        //传入callable对象来创建FutureTask对象
        FutureTask<Integer> ft = new FutureTask<Integer>(inst.new ComputeTask(i, i + ""));
        taskList.add(ft);
        //提交给线程池执行任务
        executorService.submit(ft);
    }

    System.out.println("所有子线程提交完毕，主线程接着干其他事情");
    //开始计算各计算线程统计结果
    Integer totalResult = 0;
    for (FutureTask<Integer> futureTask : taskList) {
        try {
            //futureTask的get()方法会自动阻塞，知道获取计算结果为止
            totalResult = totalResult + futureTask.get();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e2) {
            e2.printStackTrace();
        }
    }

    //关闭线程
    executorService.shutdown();
    System.out.println("多任务计算后的结果是：" + totalResult);

}

private class ComputeTask implements Callable {
    private Integer result = 0;
    private String taskName = "";

    public ComputeTask(Integer initResult, String taskName) {
        result = initResult;
        this.taskName = taskName;
        System.out.println("生成子线程计算任务：" + taskName);
    }

    public String getTaskName() {
        return this.taskName;
    }

    @Override
    public Object call() throws Exception {
        for (int i = 0; i < 100; i++) {
            result += i;
        }
        //休眠5秒钟，观察主线程行为，预期的结果是主线程会继续执行，到要取得FutureTask的结果直至等待完成
        Thread.sleep(5000);
        System.out.println("子线程计算任务：" + taskName + "计算完成");
        return result;
    }
}


2：FutureTask在高并发环境下确保任务只执行一次。
很多高并发环境下，某些任务我们往往只需要执行一次，这种使用场景FutureTask恰恰能胜任，假设一个带key的连接池，当key存在时，既直接返回一个带key的对象，当key不存在时则创建连接，这样的应用场景，通常采用的方法是使用一个map对象来存储key和对应连接池的关系。

     private ConcurrentHashMap<String, FutureTask<Connection>> connectionPool = new ConcurrentHashMap<String, FutureTask<Connection>>();
    public Connection getConnection(String key) throws Exception {
        FutureTask<Connection> connectionFutureTask = connectionPool.get(key);
        if (connectionFutureTask != null) {
            return connectionFutureTask.get();
        } else {
            Callable<Connection> callable = new Callable<Connection>() {
                @Override
                public Connection call() throws Exception {
                    return createConnection();
                }
            };
            FutureTask<Connection> newTask = new FutureTask<Connection>(callable);
            connectionFutureTask = connectionPool.putIfAbsent(key, newTask);
            if (connectionFutureTask == null) {
                connectionFutureTask = newTask;
                connectionFutureTask.run();
            }
            return connectionFutureTask.get();
        }
    }
    
    private Connection createConnection() {
        return null;
    }
    
## **信号量**    

计数信号量用来控制**同时**访问某个特定资源的**操作数量**，或者同时执行某个操作的指定数量。计数信号量还可以用来实现某种资源池，或者对容器施加边界。
acquire()从信号量获取一个许可，相似提供一个许可前一直将线程阻塞
release()释放给定数目的许可，将其返回到信号量

Semaphore可以用于做流量控制，特别公用资源有限的应用场景，比如数据库连接。假如有一个需求，要读取几万个文件的数据，因为都是IO密集型任务，我们可以启动几十个线程并发的读取，但是如果读到内存后，还需要存储到数据库中，而数据库的连接数只有10个，这时我们必须控制只有十个线程同时获取数据库连接保存数据，否则会报错无法获取数据库连接(参考http://annan211.iteye.com/blog/2115477)

     private static final int THREAD_COUNT = 30;

    private static ExecutorService threadPool = new ThreadPoolExecutor(10, 20, 5, TimeUnit.SECONDS, new SynchronousQueue<>());

    private static Semaphore s = new Semaphore(10);

    public static void main(String[] args) {
        for (int i = 0; i < THREAD_COUNT; i++) {
            final int num = i;
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        s.acquire();
                        System.out.println(Thread.currentThread().getName() + "--save data--" + num);
                        s.release();
                    } catch (InterruptedException e) {
                    }
                }
            });
        }

        threadPool.shutdown();
    }

## **栅栏：CyclicBarrier**

栅栏类似于闭锁，它能阻塞一组线程知道某个事件发生，栅栏与闭锁的区别在于，所有线程必须同时到达栅栏位置，才能继续执行，闭锁用于等待事件，而栅栏用于等待其他线程	栅栏用于实现一些协议，例如几个家庭决定在某个地方集合，所有人6点碰头，到了以后等其他人，等人到齐了再商量下一步做什么。
await() 所有参与者在此barrier调用await()之前将一直等待
public CyclicBarrier(int parties) 
public CyclicBarrier(int parties, Runnable barrierAction)
参数parties指定线程数量，当指定的线程值都到达栅栏点时，栅栏打开，线程恢复
当线程数量小于启动线程时，线程数量为10，第一个启动线程就会被阻塞。
第二个参数，在启动barrier时执行给定的屏障操作，该操作由最后一个进入barrier的线程执行

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

