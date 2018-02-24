package com.huang.sort;

/**
 * 归并排序:利用递归分治的思想，先递归划分子问题，然后合并结果。把待排序列看成由两个有序的子序列，然后合并两个子序列，
 * 然后把子序列看成由两个有序序列。
 * <p>
 * 归并排序是稳定的排序，空间复杂度为O(n)，时间复杂度为O(nlogn)。
 * <p>
 * 待排序的序列经过多层递归后，最后一层递归形成只有2个元素的序列，然后在merge中形成有序序列，
 * 然后开始两两合并，然后四四合并...最终形成有序序列。
 *
 * @author JeffreyHy
 * @date Created by  2018/2/24 16:19
 */
public final class MergeSort {
    private MergeSort() {
    }

    public static void mergeSort(int[] arr) {
        int[] tempArray = new int[arr.length];
        mergeSort(arr, tempArray, 0, arr.length - 1);
    }

    /**
     * 递归，分而治之
     * 将两个（或两个以上）有序表合并成一个新的有序表 即把待排序序列分为若干个子序列，每个子序列是有序的。
     * 然后再把有序子序列合并为整体有序序列
     *
     * @param arr
     * @param tempArray
     * @param left
     * @param right
     */
    private static void mergeSort(int[] arr, int[] tempArray, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, tempArray, left, mid);
            mergeSort(arr, tempArray, mid + 1, right);
            merge(arr, tempArray, left, mid + 1, right);
        }
    }

    /**
     * 合并左右两个子序列
     *
     * @param arr
     * @param tempArray
     * @param left
     * @param mid
     * @param right
     */
    private static void merge(int[] arr, int[] tempArray, int left, int mid, int right) {
        int leftEnd = mid - 1;
        int temPos = left;
        int numElements = right - left + 1;
        while (left <= leftEnd && mid <= right) {
            if (arr[left] <= arr[mid]) {
                tempArray[temPos++] = arr[left++];
            } else {
                tempArray[temPos++] = arr[mid++];
            }
        }
        while (left <= leftEnd) {//copy左子序列剩余元素
            tempArray[temPos++] = arr[left++];
        }
        while (mid <= right) {
            tempArray[temPos++] = arr[mid++];//copy右子序列的元素
        }
        //复制缓存数组中的元素至原数组
        for (int i = 0; i < numElements; i++, right--) {
            arr[right] = tempArray[right];
        }
    }

}
