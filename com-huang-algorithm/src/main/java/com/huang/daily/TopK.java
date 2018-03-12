package com.huang.daily;

import com.huang.datastructure.Heap;
import com.huang.util.ArrayUtils;

import java.util.ArrayList;
import java.util.List;

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
                heapArray[0] = array[i];//更新堆顶
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
                heapArray[0] = array[i];//更新堆顶
                Heap.adjustMinHeap(heapArray, 0, heapArray.length);
            }
        }
        return heapArray;
    }

    /**
     * 寻找Ｎ个数中的第Ｋ大的数,可以将问题转化寻找Ｎ个数中第K大的数的问题。对于一个给定的数p,
     * 可以在O(N)的时间复杂度内找出所有不小于P的数。
     * <p>
     * 可以使用二分查找的算法来寻找Ｎ个数中第K大的数，假设N个数中最大的数为Vmax,最小的数为Vmin,
     * 那么Ｎ个数中第K大的数一定在区间[Vmin,Vmax]之间。然后在这个区间使用二分查找算法。
     * <p>
     * 该算法实际应用效果不佳，尤其是不同的数据类型需要确定max - min > delta，由于delta的取值要比所有N
     * 个数中的任意两个不想等的元素差值之最小值小，因此时间复杂度跟数据分布有关。
     * 整个算法的时间复杂度为O(N * log（Vmax-Vmin）/delta),在数据分布平均的情况下，时间复杂度为O(N * logN)
     *
     * @param array
     * @param k
     * @return
     */
    public static List<Integer> findTopKMax_3(int[] array, int k) {
        int max = ArrayUtils.max(array);
        int min = ArrayUtils.min(array);
        List<Integer> topKList = new ArrayList<>();
        int key = findK(array, max, min, k);
        for (int i = 0; i < array.length; i++) {
            if (array[i] >= key) {
                topKList.add(array[i]);
            }
        }
        return topKList;
    }

    /**
     * 寻找第K大的元素
     *
     * @param array
     * @param max
     * @param min
     * @param k
     * @return
     */
    private static int findK(int[] array, int max, int min, int k) {
        while (max - min > 1) {
            int mid = (max + min) / 2;
            int num = findKNum(array, mid);
            if (num >= k) {
                min = mid;
            } else {
                max = mid;
            }
        }
        return min;
    }

    /**
     * 统计不小于key的数据个数
     *
     * @param array
     * @param key
     * @return
     */
    private static int findKNum(int[] array, int key) {
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] >= key)
                sum++;
        }
        return sum;
    }

    /**
     * 对于所有N个数都是正整数，且取值范围不大，可以考虑申请空间，记录每个整数出现的次数，然后再从大到小取最大的K个。
     * 假设所有整数都在（0，maxN）区间，利用一个数组count[maxN]来记录每个整数出现的次数。
     * count[i]表示整数i在N个数中出现的次数。只需要扫描一遍就可以得到count数组，然后寻找第K大的元素。
     * <p>
     * 极端情况下，如果 N 个整数各不相同，我们甚至只需要一个 bit 来存储这个整数是否存在。
     *
     * @param array
     * @param k
     * @return
     */
    public static List<Integer> findTopKMax_4(int[] array, int k) {
        int max = ArrayUtils.max(array);
        int count[] = new int[max + 1];
        for (int i = 0; i < array.length; i++) {
            count[array[i]] += 1;
        }
        List<Integer> topKList = new ArrayList<>();
        for (int sumCount = 0, j = count.length - 1; j >= 0; j--) {
            int c = count[j];
            sumCount += c;
            if (c > 0) {
                for (int i = 0; i < c; i++) {
                    topKList.add(j);
                }
            }
            if (sumCount >= k) {
                break;
            }

        }
        return topKList;
    }

}
