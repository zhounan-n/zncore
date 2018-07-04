package com.zn.algorithm;

/**
 * 二分查找
 * Created by zhoun on 2018/7/4.
 **/
public class BinaryChop {

    public static int binarySearch(int[] arr, int target) {
        int low = 0;
        int high = arr.length;
        while (low <= high) {
            int middle = (low + high) / 2;
            if (target == arr[middle]) {
                return middle;
            } else if (target < arr[middle]) {
                high = middle - 1;
            } else {
                low = middle + 1;
            }
        }
        return -1;
    }

    public static void main() {

    }

}
