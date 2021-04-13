package com.hujiang.mytest;

/**
 * Date:  2019-12-18
 * Time:  22:49
 * Author: jianglong
 * -----------------------------
 * MISSION
 */
public class TestCode {
    public static void main(String[] args) {
        System.out.println(isPalindrome(-121));
        int a=10;
        System.out.println(((int) (1.252 * 1000)) / a * a / 1000f);

    }

    public static boolean isPalindrome(int x) {
        String origin = String.valueOf(x);
        return origin.equals(new StringBuilder(origin).reverse().toString());
    }

}
