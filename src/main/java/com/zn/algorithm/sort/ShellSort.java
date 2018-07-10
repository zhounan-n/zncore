package com.zn.algorithm.sort;

/**
 * 希尔排序（插入排序的改进，为非稳定算法）
 * 对于直接插入排序问题，数据量巨大时。将数的个数设为n，取奇数k=n/2，将下标差值为k的数分为一组，构成有序序列。
 * 再取k=k/2 ，将下标差值为k的书分为一组，构成有序序列。重复第二步，直到k=1执行简单插入排序。
 * <p>
 * 代码实现：
 * 首先确定分的组数。然后对组中元素进行插入排序。 然后将length/2，重复1,2步，直到length=0为止。
 * Created by zhoun on 2018/7/10.
 **/
public class ShellSort {

    public void shellSort(int[] arr) {
        int length = arr.length;
        while (length != 0) {
            length = length / 2;
            //分组
            for (int i = 0; i < length; i++) {
                //元素从第二个开始
                for (int j = i + length; j < arr.length; j += length) {
                    //k为有序序列最后一个数
                    int k = j - length;
                    //要插入的元素
                    int tmp = arr[j];
                    while (k > 0 && tmp < arr[k]) {
                        k -= length;
                    }
                    arr[k + length] = tmp;
                }
            }
        }
    }

}
