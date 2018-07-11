package com.zn.algorithm.sort;

/**
 * (基本不用 太简单了)
 * 冒泡排序:将序列中所有元素两两比较，将大的元素放在后面
 * 将剩余序列中的数两两比较，将大的数放在后面
 * 重复第二步，直到只剩下一个数
 * Created by zhoun on 2018/7/10.
 **/
public class BubbleSort {

    public void sort(int[] arr) {
        int length = arr.length;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
        }
    }

}
