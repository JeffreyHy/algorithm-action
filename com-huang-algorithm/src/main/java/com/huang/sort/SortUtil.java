package com.huang.sort;

/**
 * @author JeffreyHy
 * @date Created by  2018/2/24 14:19
 */
public final class SortUtil {
    private SortUtil() {
    }

    /**
     * 交换
     *
     * @param arr
     * @param i
     * @param j
     */
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
