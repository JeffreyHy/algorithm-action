package com.huang.sort;

/**
 * 选择排序:原理是每一次从待排序的数据元素中选出最小（或最大）的一个元素，存放在序列的起始位置，直到全部待排序的数据元素排完。
 * <p>
 * 如：对5,3,8,6,4这个无序序列进行简单选择排序，首先要选择5以外的最小数来和5交换，也就是选择3和5交换，
 * 一次排序后就变成了3,5,8,6,4.对剩下的序列一次进行选择和交换，最终就会得到一个有序序列。
 * 其实选择排序可以看成冒泡排序的优化，因为其目的相同，只是选择排序只有在确定了最小数的前提下才进行交换，大大减少了交换的次数。
 * <p>
 * 选择排序的时间复杂度为O(n^2)
 *
 * @author JeffreyHy
 * @date Created by  2018/2/24 14:17
 */
public final class SelectSort {
    private SelectSort() {
    }

    public static void sort(int[] arr) {
        if (null == arr || arr.length < 0)
            return;
        int index ;
        for (int i = 0; i < arr.length - 1; i++) {
            index = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[index]) {//升序
                    index = j;
                }
            }
            if (index != i) {
                SortUtil.swap(arr, i, index);
            }
        }
    }

}
