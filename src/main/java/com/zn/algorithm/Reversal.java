package com.zn.algorithm;

/**
 * 字符串的反转
 * Created by zhoun on 2018/4/9.
 **/
public class Reversal {

    public static void main(String[] args) {
        String str = "ILoveJavaAndPython";
        char[] strArray = str.toCharArray();
        int len = strArray.length;
        char temp;
        for (int i = 0; i < len / 2; i++) {
            temp = strArray[i];
            strArray[i] = strArray[len - i - 1];
            strArray[len - i - 1] = temp;
        }
        String strAfter = String.valueOf(strArray);
        System.out.println("str before:" + str);
        System.out.println("str after:" + strAfter);

    }

}
