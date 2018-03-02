package com.zn.jdk8;

import java.util.*;
import java.util.stream.Collectors;

/**
 * stream
 * Created by zhoun on 2018/3/2.
 **/
public class NewFeturesTest5 {

    public static void main(String[] args) {
        System.out.println("USING JAVA 7: ");
        //统计空字符串的数量
        List<String> strings = Arrays.asList("efg", "", "abc", "bc", "ghij", "", "lmn");
        System.out.println("List:" + strings);
        long count = getCountEmptyStringUsingJava7(strings);
        System.out.println("Empty strings count: " + count);

        count = getCountLength3UsingJava7(strings);
        System.out.println("strings of length 3：" + count);

        String mergedString = getMergedStringUsingJava7(strings, ",");
        System.out.println("merged string : " + mergedString);

        List<String> filtered = deleteEmptyStringUsingJava7(strings);
        System.out.println("filtered string : " + filtered);
        List<Integer> numbers = Arrays.asList(2, 3, 3, 2, 5, 5, 7);
        //计算数字的平方
        List<Integer> squires = getSquares(numbers);
        System.out.println("square list : " + squires);
        List<Integer> integers = Arrays.asList(1, 2, 13, 4, 15, 6, 17, 8, 19);


        // 输出10个随机数
        System.out.println("Random Numbers: ");
        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            System.out.println(random.nextInt());
        }


        System.out.println("USING JAVA 8 : -----------");
        System.out.println("LIST:" + strings);
        count = strings.stream().filter(string -> string.isEmpty()).count();
        System.out.println("empty string count :" + count);

        count = strings.stream().filter(string -> string.length() == 3).count();
        System.out.println("strings of length 3 :" + count);

        filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
        System.out.println("filtered string :" + filtered);

        mergedString = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.joining(","));
        System.out.println("merged string :" + mergedString);

        squires = numbers.stream().map(i -> i * i).distinct().collect(Collectors.toList());
        System.out.println("square list: " + squires);

        IntSummaryStatistics stats = integers.stream().mapToInt((x) -> x).summaryStatistics();

        //输出结果
        System.out.println("highest number in list :" + stats.getMax());
        System.out.println("lowest number in list: " + stats.getMin());
        System.out.println("sum of all numbers :" + stats.getSum());
        System.out.println("average of all numbers: " + stats.getAverage());
        System.out.println("random numbers :");

        random.ints().limit(10).sorted().forEach(System.out::println);

        //并行处理
        strings.parallelStream().filter(string -> string.isEmpty()).count();
        System.out.println("empty string count : ");



    }

    //使用java7版本提供的api来统计计算空字符串的数量
    public static int getCountEmptyStringUsingJava7(List<String> list) {
        int count = 0;
        for (String str : list) {
            if (str.isEmpty()) {
                count++;
            }
        }
        return count;
    }

    //使用java7版本就提供的api来计算长度为3字符的字符串数量
    public static int getCountLength3UsingJava7(List<String> list) {
        int count = 0;
        for (String str : list) {
            if (str.length() == 3) {
                count++;
            }
        }
        return count;
    }

    //使用java7版本提供的api来删除空串
    public static List<String> deleteEmptyStringUsingJava7(List<String> list) {
        List<String> newList = new ArrayList<>();
        for (String str : list) {
            if (!str.isEmpty()) {
                newList.add(str);
            }
        }
        return newList;
    }

    //使用java7提供的api来获取后台合并后的字符串
    private static String getMergedStringUsingJava7(List<String> list, String seprator) {
        StringBuilder sb = new StringBuilder();
        for (String str : list) {
            if (!str.isEmpty()) {
                sb.append(str);
                sb.append(seprator);
            }
        }
        String mergeredString = sb.toString();
        return mergeredString.substring(0, mergeredString.length() - 2);
    }

    public static List<Integer> getSquares(List<Integer> numbers) {
        List<Integer> squareList = new ArrayList<>();
        for (Integer s : numbers) {
            Integer sq = new Integer(s.intValue() * s.intValue());
            if (!squareList.contains(sq)) {
                squareList.add(sq);
            }
        }
        return squareList;
    }

   /* public static int getMax() {

    }*/

   /* public static int getMin() {

    }

    public static int getSum() {

    }

    public static int getAverage() {

    }*/
}
