package com.huang.sort;

import com.huang.util.SortUtil;

/**
 * 快速排序:基于分治的思想，是冒泡排序的改进型。首先在数组中选择一个基准点，然后分别从数组的两端扫描数组，
 * 设两个指示标志（lo指向起始位置，hi指向末尾)，首先从后半部分开始，如果发现有元素比该基准点的值小，
 * 就交换lo和hi位置的值，然后从前半部分开始扫秒，发现有元素大于基准点的值，就交换lo和hi位置的值，如此往复循环，
 * 直到lo>=hi,然后把基准点的值放到hi这个位置。一次排序就完成了。以后采用递归的方式分别对前半部分和后半部分排序，
 * 当前半部分和后半部分均有序时该数组就自然有序了。
 * <p>
 * 快速排序是不稳定的，其平均时间复杂度是O(nlgn)，最坏的情况是O(n^2)。
 * <p>
 * 基准点的选取一般有三种方法：固定切分，随机切分和三取样切分。固定切分的效率并不是太好，随机切分是常用的一种切分，效率比较高，
 * 最坏情况下时间复杂度有可能为O(N2).对于三数取中选择基准点是最理想的一种。
 *
 * @author JeffreyHy
 * @date Created by  2018/2/24 14:35
 */
public final class QuickSort {
    private QuickSort() {
    }

    public static void quickSort(int[] arr) {
        if (null == arr || arr.length < 0)
            return;
        execSort1(arr, 0, arr.length - 1);
    }

    /**
     * 递归执行排序操作，最坏的情况：空间复杂度是 O(n)
     *
     * @param arr
     * @param left
     * @param right
     */
    private static void execSort1(int[] arr, int left, int right) {
        if (left >= right)
            return;
        int index = partitionMid(arr, left, right);
        //按基准点的位置，将数组拆分为两个子序列再进行排序
        execSort1(arr, left, index - 1);
        execSort1(arr, index + 1, right);
    }

    /**
     * 使用尾递归优化，分割成两个序列时，只对其中一个递归进去，
     * 另一个序列仍可以在这一函数内继续划分，可以显著减小栈的大小
     * 最坏的情况空间复杂度仍然可能为 O(n)
     *
     * @param arr
     * @param left
     * @param right
     */
    private static void execSort2(int[] arr, int left, int right) {
        while (left < right) {
            int index = partitionMid(arr, left, right);
            //按基准点的位置，将数组拆分为两个子序列再进行排序
            execSort2(arr, left, index - 1);
            left = index + 1;
        }
    }

    /**
     * 在尾递归的基础上再进行优化，使得每次都对包含数组元素较少的那一个划分部分进行递归调用
     * 在最坏情况下，快速排序的空间复杂度就会降低到 O(logn)
     *
     * @param arr
     * @param left
     * @param right
     */
    private static void execSort3(int[] arr, int left, int right) {
        while (left < right) {
            int index = partitionMid(arr, left, right);
            if (index - left < right - index) {
                execSort3(arr, left, index - 1);
                left = index + 1;
            } else {
                execSort3(arr, index + 1, right);
                right = index - 1;
            }
        }
    }

    /**
     * 划分操作，先移动右指针，遇到大于基准点的元素，停止，再移动左指针，遇到小于基准点的元素，
     * 停止，交换左指针和右指针指向的元素；循环，直到left>=right；最后交换左指针和基准点的位置
     *
     * @param arr
     * @param left
     * @param right
     * @return
     */
    private static int partitionTemp(int[] arr, int left, int right) {
        int pivotKey = arr[left];//基准点
        int pivotPointer = left;//基准点的位置
        while (left < right) {
            while (left < right && arr[right] >= pivotKey) {//从右边开始查找大于基准点的元素
                right--;
            }
            while (left < right && arr[left] <= pivotKey) {//从左边开始查找小于基准点的元素
                left++;
            }
            SortUtil.swap(arr, left, right);//交换位置，大的交换到右边，把小的交换到左边
        }
        SortUtil.swap(arr, pivotPointer, left);//将基准点元素交换到left和right相等的位置
        return left;
    }

    /**
     * 该方法比partitionTemp效率更高
     * 划分操作，先移动右指针，遇到大于基准点的元素，停止，交换左指针和右指针指向的元素；再移动左指针，遇到小于基准点的元素，停止，
     * 交换右指针和左指针指向的元素；循环，直到left>=right；最后修改左指针指向的元素为基准点
     *
     * @param arr
     * @param left
     * @param right
     * @return
     */
    private static int partition(int[] arr, int left, int right) {
        int pivotKey = arr[left];
        while (left < right) {
            while (left < right && arr[right] >= pivotKey) {
                right--;
            }
            arr[left] = arr[right];
            while (left < right && arr[left] <= pivotKey) {
                left++;
            }
            arr[right] = arr[left];
        }
        arr[left] = pivotKey;
        return left;
    }

    /**
     * 使用三数取中算法确定基准点
     *
     * @param arr
     * @param left
     * @param right
     * @return
     */
    private static int partitionMid(int[] arr, int left, int right) {
        int mid = left + (right - left) / 2;
        if (arr[mid] > arr[right]) {
            SortUtil.swap(arr, mid, right);
        }
        if (arr[left] > arr[right]) {
            SortUtil.swap(arr, left, right);
        }
        if (arr[mid] > arr[left]) {
            SortUtil.swap(arr, mid, left);
        }
        int pivotKey = arr[left];
        while (left < right) {
            while (left < right && arr[right] >= pivotKey) {
                right--;
            }
            arr[left] = arr[right];
            while (left < right && arr[left] <= pivotKey) {
                left++;
            }
            arr[right] = arr[left];
        }
        arr[left] = pivotKey;
        return left;
    }

}
