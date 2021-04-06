package com.hyq.strategy;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @author lucky
 * @date 2021/3/31 9:03
 */
public class Sorter {

    static int[] arr = {1, 3, 2, 5, 7, 9, 10, 1};

    public static void main(String[] args) {
        int temp = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }

//        for (int i = 0; i < arr.length; i++) {
//            for (int j = 0; j < arr.length; j++) {
//            i
//            }
//        }
        System.out.println(Arrays.toString(arr));

    }
}
