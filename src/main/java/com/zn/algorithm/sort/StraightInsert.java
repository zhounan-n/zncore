package com.zn.algorithm.sort;

/**
 * 直接插入排序
 * 假设前面(n-1) [n>=2] 个数已经是排好顺序的，现在要把第n个数插到前面的有序数中，
 * 使得这n个数也是排好顺序的。如此反复循环，直到全部排好顺序。
 *
 * 实现：
 *      首先设定插入次数，即循环次数，for(int i=1;i<length;i++)，1个数的那次不用插入。
 *      设定插入数和得到已经排好序列的最后一个数的位数。insertNum和j=i-1。
 *      从最后一个数开始向前循环，如果插入数小于当前数，就将当前数向后移动一位。
        将当前数放置到空着的位置，即j+1。
 *
 * Created by zhoun on 2018/7/10.
 **/
public class StraightInsert {


    public void insert(int[] arr){
        int lenth = arr.length;
        //要插入的数
        int insertNum;
        for (int i=1;i<arr.length;i++) {
            insertNum = arr[i];
            int j = i-1;
            //从后往前循环 将大于insertNum的数向后移
            while(j>=0&&arr[j]>insertNum){
                arr[j+1]=arr[j];
                j--;
            }
            //找到位置 插入当前元素
            arr[j+1]=insertNum;
        }
    }

}
