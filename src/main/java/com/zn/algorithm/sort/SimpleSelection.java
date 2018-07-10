package com.zn.algorithm.sort;

/**
 * 简单选择排序算法
 * 常用于取序列中最大最小几个数时
 * 遍历整个序列，将最小的数放在最前面。
 * 遍历剩下的序列，将最小的数放在最前面。
 * 重复第二步，直到只剩下一个数。
 * <p>
 * <p>
 * 代码实现：
 * 首先确定循环次数，并且记住当前数字和当前位置。
 * 将当前位置后面所有的数与当前数字进行对比，小数赋值给key，并记住小数的位置。
 * 将当前位置后面所有的数与当前数字进行对比，小数赋值给key，并记住小数的位置。
 * Created by zhoun on 2018/7/10.
 **/
public class SimpleSelection {

    public void selectSort(int[] arr) {
        int length = arr.length;
        //循环次数
        for (int i = 0; i < length; i++) {
            int value = arr[i];
            int position = i;
            //找到最小的值和位置
            for (int j = i + 1; j < length; j++) {
                if (arr[j] < value) {
                    value = arr[j];
                    position = j;
                }
            }
            //进行交换
            arr[position] = arr[i];
            arr[i] = value;

        }
    }

}
