package com.huang.sort;

import com.huang.util.SortUtil;

/**
 * 三路快排实战
 * 将只有0,1,2三种元素的数组排序
 *
 * @author JeffreyHy
 * @date Created by  2018/3/16 17:47
 */
public class QuickSort3WaysAction {
    /**
     * 数组最小长度
     */
    public static final int ARRAY_MIN_LEN = 2;

    /**
     * 计数排序实现
     * 需要经历四次遍历，时间复杂度O(n)
     *
     * @param arr
     */
    public static void countSort(int[] arr) {
        if (null == arr || arr.length < ARRAY_MIN_LEN) {
            return;
        }
        int[] count = new int[3];
        for (int i = 0; i < arr.length; i++) {
            assert (arr[i] >= 0 && arr[i] <= 2);
            count[arr[i]] += 1;
        }
        int index = 0;
        for (int i = 0; i < count[0]; i++) {
            arr[index++] = 0;
        }
        for (int i = 0; i < count[1]; i++) {
            arr[index++] = 1;
        }
        for (int i = 0; i < count[2]; i++) {
            arr[index++] = 2;
        }
    }

    /**
     * 使用三路快排，时间复杂度O(n)，只需要一次循环
     *
     * @param arr
     */
    public static void quickSort3Ways(int[] arr) {
        int left = 0;
        int i = 0;
        int right = arr.length - 1;
        while (i <= right) {
            if (arr[i] == 1) {
                i++;
            } else if (arr[i] == 0) {
                //交换到左端
                SortUtil.swap(arr, left++, i++);
            } else {
                assert (arr[i] == 2);
                //交换到右端
                SortUtil.swap(arr, i, right--);
            }
        }
    }
}
