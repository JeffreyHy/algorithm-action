package com.huang.sort;

import com.huang.util.SortUtil;

/**
 * 冒泡排序：其大体思想就是通过与相邻元素的比较和交换来把小的数交换到最前面。
 * <p>
 * 如：对5,3,8,6,4进行冒泡排序。首先从后向前冒泡，4和6比较，把4交换到前面，序列变成5,3,8,4,6。同理4和8交换，
 * 变成5,3,4,8,6,3和4无需交换。5和3交换，变成3,5,4,8,6,3.这样一次冒泡就完了，把最小的数3排到最前面了。
 * <p>
 * 冒泡排序的时间复杂度为O(n^2)。
 *
 * @author JeffreyHy
 * @date Created by  2018/2/24 14:10
 */
public final class BubbleSort {
    private BubbleSort() {
    }

    public static void ascendingOrder(int[] arr) {
        if (null == arr || arr.length < 0)
            return;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = arr.length - 1; j > i; j--) {
                if (arr[j] < arr[j - 1]) { //升序
                    SortUtil.swap(arr, j - 1, j);
                }
            }
        }
    }

    public static void descendingOrder(int[] arr) {
        if (null == arr || arr.length < 0)
            return;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = arr.length - 1; j > i; j--) {
                if (arr[j] > arr[j - 1]) { //降序
                    SortUtil.swap(arr, j - 1, j);
                }
            }
        }
    }

}
