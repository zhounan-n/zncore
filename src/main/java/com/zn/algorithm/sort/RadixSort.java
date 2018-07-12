package com.zn.algorithm.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 基数排序
 * 用于大量数，很长的数进行排序时
 * 将所有的数的个位数取出，按照个位数进行排序，构成一个序列。
 * 将新构成的所有的数的十位数取出，按照十位数进行排序，构成一个序列。
 * Created by zhoun on 2018/7/10.
 **/
public class RadixSort {

    public void sort(int[] arr) {
        //首先确定排序的次数(最大数有几位)
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (max < arr[i]) {
                max = arr[i];
            }
        }
        int time = 0;
        while (max > 0) {
            max /= 10;
            time++;
        }

        //建立10个队列
        List<ArrayList<Integer>> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new ArrayList<Integer>());
        }

        //进行time次分配和收集
        for (int i = 0; i < time; i++) {
            //分配数组元素
            for (int j = 0; j < arr.length; j++) {
                //得到数字的第time+1位 Math.pow(底数，几次方)
                int x = arr[j] % (int) Math.pow(10, i + 1) / (int) Math.pow(10, i);
                ArrayList<Integer> queue = list.get(x);
                queue.add(arr[j]);
                list.set(x, queue);
            }
            //元素计数器
            int count = 0;
            //收集队列元素
            for (int k = 0; k < 10; k++) {
                while (list.get(k).size() > 0) {
                    ArrayList<Integer> queue3 = list.get(k);
                    arr[count] = queue3.get(0);
                    queue3.remove(0);
                    count++;
                }
            }
        }

    }

    public static void main(String[] args) {
        int[] a = new int[10];
        for (int i = 1; i < a.length; i++) {
            //a[i]=(int)(new Random().nextInt(100));
            a[i] = (int) (Math.random() * 100);
        }
        System.out.println("排序前的数组为：" + Arrays.toString(a));
        RadixSort s = new RadixSort();
        //排序方法测试
        s.sort(a);
        System.out.println("排序后的数组为：" + Arrays.toString(a));
    }

}
