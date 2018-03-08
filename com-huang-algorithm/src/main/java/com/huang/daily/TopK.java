package com.huang.daily;

import com.huang.datastructure.Heap;

/**
 * 问题描述：给定n个整数，求其前k大或前k小的问题，简称TOP-K问题
 * <p>
 * 解法1：最简单且最容易想到的办。对数组进行排序（快速排序或堆排序），然后取最大或最小的K个元素，
 * 总的时间复杂度为O(N*logN)+O(K)=O(N*logN)。存在的问题：
 * ->快速排序的平均复杂度为O(N*logN)，但最坏时间复杂度为O(n2)，不能始终保证较好的复杂度
 * ->只需要前k大的,对其余不需要的数也进行了排序，浪费了大量排序时间
 *
 * @author JeffreyHy
 * @date Created by  2018/3/7 11:28
 */
public final class TopK {
    private TopK() {
    }

    /**
     * 解法2
     * <p>
     * 算法思路：利用快速排序的思想，在数组中随机找一个元素key,将数组分成两部分Sa和Sb,
     * 其中Sa的元素>=key，Sb的元素<key，分析两种情况：
     * ->若Sa中元素的个数大于或等于k,则在Sa中查找最大的k个数
     * ->若Sa中元素的个数小于k,其个数为len,则在Sb中查找k-len个数字
     * 这样递归下去，不断把问题分解为更小的问题。
     * 平均时间复杂度为O(N * logk)
     *
     * @param array 原始数组
     * @param left  下边界
     * @param right 上边界
     * @param k     取k个数
     * @return
     */
    public static int findTopKMax_1(int[] array, int left, int right, int k) {
        int index = -1;
        if (left < right) {
            int pos = partition(array, left, right);
            int len = pos - left + 1;
            if (len == k) {
                index = pos;
            } else if (len < k) {//Sa中元素个数小于K，到Sb中查找k-len个数字
                index = findTopKMax_1(array, pos + 1, right, k - len);
            } else {//Sa中元素的个数大于或等于k
                index = findTopKMax_1(array, left, pos - 1, k);
            }
        }
        return index;
    }

    /**
     * 按基准点划分数组，左边的元素大于基准点，右边的元素小于基准点
     *
     * @param array
     * @param left
     * @param right
     * @return
     */
    public static int partition(int[] array, int left, int right) {
        int x = array[left];//基准点，随机选择
        do {
            while (array[right] < x && left < right)//从后向前扫描，找到第一个比基准点大的元素
                right--;
            if (left < right) {
                array[left] = array[right];//大元素前移
                left++; //left位置无需再比较
            }
            while (array[left] >= x && left < right) //从前向后扫描，找到第一个比基准点小的元素
                left++;
            if (left < right) {
                array[right] = array[left];//小元素后移
                right--;//right位置无需再比较
            }
        } while (left < right);
        array[left] = x;
        return left;
    }

    /**
     * 解法3
     * 算法思路：当数组中元素个数非常大时，如：100亿，这时候数据不能全部加载到内存，就要求我们尽可能少的遍历所有数据。
     * 为了查找Top k大的数，我们可以使用小根堆来存储最大的K个元素。小根堆的堆顶元素就是最大K个数中最小的一个。
     * 每次考虑下一个数x时,如果x比堆顶元素小，则不需要改变原来的堆。如果想x比堆顶元素打，那么用x替换堆顶元素，
     * 同时，在替换之后，x可能破坏最小堆的结构，需要调整堆来维持堆的性质。
     * <p>
     * 时间复杂度：O(N * logk)
     * 适合场景：采用堆排序的算法，不会改变输入数据，且不会占用太多内存空间（容纳前K个元素），尤其适合处理海量数据的场景
     * <p>
     * 大根堆查找前k小的元素
     *
     * @param array
     * @param k
     * @return
     */
    public static int[] findTopKMin_1(int[] array, int k) {
        int heapArray[] = new int[k];
        for (int i = 0; i < k; i++) {
            heapArray[i] = array[i];
        }
        Heap.buildMaxHeap(heapArray);
        for (int i = k; i < array.length; i++) {
            if (array[i] < heapArray[0]) {
                heapArray[0] = array[i];
                Heap.adjustMaxHeap(heapArray, 0, heapArray.length);
            }
        }
        return heapArray;
    }

    /**
     * 小根堆查找前K大的元素
     *
     * @param array
     * @param k
     * @return
     */
    public static int[] findTopKMax_2(int[] array, int k) {
        int heapArray[] = new int[k];
        for (int i = 0; i < k; i++) {
            heapArray[i] = array[i];
        }
        Heap.buildMinHeap(heapArray);
        for (int i = k; i < array.length; i++) {
            if (array[i] > heapArray[0]) {
                heapArray[0] = array[i];
                Heap.adjustMinHeap(heapArray, 0, heapArray.length);
            }
        }
        return heapArray;
    }
}
