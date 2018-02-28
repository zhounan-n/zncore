package com.zn.multithread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 同步工具类之FutureTask(获取计算值)
 * Created by zhoun on 2018/2/27.
 **/
public class TestFutureTask {

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


}
