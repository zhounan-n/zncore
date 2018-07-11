package com.zn.algorithm.sort;

import java.util.Arrays;

/**
 * 归并排序
 * 分而治之，递归
 * 一分为二知道不可再拆分，合并相邻有序子序列
 * Created by zhoun on 2018/7/10.
 **/
public class MergeSort {

    public static void sort(int[] arr) {
        //在排序前 先建好一个长度等于原数组长度的临时数组，避免递归中频繁开辟空间
        int[] tmp = new int[arr.length];
        sort(arr, 0, arr.length - 1, tmp);
    }

    public static void sort(int[] arr, int left, int right, int[] tmp) {
        if (left < right) {
            int mid = (left + right) / 2;
            //左边归并排序
            sort(arr, left, mid, tmp);
            //右边归并排序
            sort(arr, mid + 1, right, tmp);
            //讲两个有序子数组合并操作
            merge(arr, left, mid, right, tmp);
        }

    }

    public static void merge(int[] arr, int left, int mid, int right, int[] tmp) {
        //左序列指针
        int i = left;
        //右序列指针
        int j = mid + 1;
        //临时数组指针
        int t = 0;
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                tmp[t++] = arr[i++];
            } else {
                tmp[t++] = arr[j++];
            }
        }
        //将左边剩余元素填充进tmp中
        while (i <= mid) {
            tmp[t++] = arr[i++];
        }
        //将右边剩余元素填充进tmp中
        while (j <= right) {
            tmp[t++] = arr[j++];
        }

        t = 0;
        //将tmp中的元素全部拷贝进原数组中
        while (left <= right) {
            arr[left++] = tmp[t++];
        }
    }


    public static void main(String[] args) {
        int[] arr = {9, 8, 7, 6, 5, 4, 3, 2, 1};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

}
