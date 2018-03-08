package com.huang.sort;

import com.huang.datastructure.Heap;
import com.huang.util.SortUtil;

/**
 * 堆排序是利用堆这种数据结构而设计的一种排序算法，堆排序是一种选择排序，
 * 它的最坏，最好，平均时间复杂度均为O(nlogn)，它也是不稳定排序。
 * 升序排序就采用大根堆，降序则采用小根堆
 * 排序思想，以升序为例：
 * 第一步，先将待排序的序列构造成一个大根堆
 * 第二步，将堆顶元素与末尾元素交换，然后将剩余n-1个元素重新构造成一个堆，
 * 新的堆顶元素即为n个元素的次小值，如此反复执行即可得到有序的序列
 *
 * @author JeffreyHy
 * @date Created by  2018/3/7 19:53
 */
public final class HeapSort {
    private HeapSort() {
    }

    /**
     * 升序，采用大根堆
     *
     * @param array
     */
    public static void ascendingOrder(int array[]) {
        if (array == null || array.length == 0) {
            return;
        }
        Heap.buildMaxHeap(array);//构建大根堆
        for (int i = array.length - 1; i >= 0; i--) {
            SortUtil.swap(array, 0, i);//堆顶元素压入当前数组尾部
            Heap.adjustMaxHeap(array, 0, i - 1);//抛弃堆尾调整堆
        }
    }
    /**
     * 升序，采用小根堆
     *
     * @param array
     */
    public static void descendingOrder(int array[]){
        if (array == null || array.length == 0) {
            return;
        }
        Heap.buildMinHeap(array);//构建小根堆
        for (int i = array.length - 1; i >= 0; i--) {
            SortUtil.swap(array, 0, i);//堆顶元素压入当前数组尾部
            Heap.adjustMinHeap(array, 0, i - 1);//抛弃堆尾调整堆
        }
    }
}
