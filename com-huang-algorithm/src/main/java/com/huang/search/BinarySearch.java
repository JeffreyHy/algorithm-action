package com.huang.search;

/**
 * 二分查找算法
 * 算法思想：又叫折半查找，要求待查找的序列有序。每次取中间位置的值与待查关键字比较，
 * 如果中间位置的值比待查关键字大，则在前半部分循环这个查找的过程，如果中间位置的值比待查关键字小，
 * 则在后半部分循环这个查找的过程。直到查找到了为止，否则序列中没有待查的关键字。
 *
 * @author JeffreyHy
 * @date Created by  2018/3/6 17:45
 */
public final class BinarySearch {

    private BinarySearch() {
    }

    /**
     * 非递归实现
     *
     * @param array
     * @param left
     * @param right
     * @param key
     * @return
     */
    public static int biSearch(int array[], int left, int right, int key) {
        int mid;
        if (array[right] < key) {
            return right + 1;
        }
        while (left < right) {
            mid = (left + right) / 2;
            if (array[mid] < key) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}
