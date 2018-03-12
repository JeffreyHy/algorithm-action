package com.huang.util;

/**
 * @author JeffreyHy
 * @date Created by  2018/3/6 16:29
 */
public final class ArrayUtils {
    private ArrayUtils() {
    }

    /**
     * 求数组中最大值
     *
     * @param array
     * @return
     */
    public static int max(int[] array) {
        int max = array[0];
        for (int i = 0; i < array.length; i++) {
            if (max < array[i]) {
                max = array[i];
            }
        }
        return max;
    }

    /**
     * 求数组中最小值
     *
     * @param array
     * @return
     */
    public static int min(int[] array) {
        int min = array[0];
        for (int i = 0; i < array.length; i++) {
            if (min > array[i]) {
                min = array[i];
            }
        }
        return min;
    }
}
