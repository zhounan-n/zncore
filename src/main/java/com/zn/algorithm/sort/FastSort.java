package com.zn.algorithm.sort;

/**
 * 快速排序(要求时间最快时)
 * 选择第一个数为p，小于p的数放在左边，大于p的数放在右边。
 * 递归的将p左边和右边的数都按照第一步进行，直到不能递归。
 * Created by zhoun on 2018/7/10.
 **/
public class FastSort {

    public void quickSort(int[] arr, int start, int end) {
        if (start < end) {
            //选基准值
            int baseNum = arr[start];
            //记录中间值
            int midNum;
            int i = start;
            int j = end;
            do {
                while (arr[i] < baseNum && i < end) {
                    i++;
                }
                while (arr[j] > baseNum && j > start) {
                    j--;
                }
                if (i <= j) {
                    midNum = arr[i];
                    arr[i] = arr[j];
                    arr[j] = midNum;
                    i++;
                    j--;
                }
            } while (i <= j);
            if (start < j) {
                quickSort(arr, start, j);
            }

            if (end > i) {
                quickSort(arr, i, end);
            }
        }
    }


}
