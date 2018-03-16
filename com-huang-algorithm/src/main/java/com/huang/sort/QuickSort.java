package com.huang.sort;

import com.huang.util.SortUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.Collectors;

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
 * 最坏情况下时间复杂度有可能为O(n^2)，对于三数取中选择基准点是最理想的一种。
 * <p>
 * 对于数组比较小（N<=20）时，快速排序不如插入排序
 *
 * @author JeffreyHy
 * @date Created by  2018/2/24 14:35
 */
public final class QuickSort {
    private static final Logger logger = LoggerFactory.getLogger(QuickSort.class);

    private QuickSort() {
    }

    public static void ascendingOrder(int[] arr) {
        if (null == arr || arr.length < 2)
            return;
        execSortV1(arr, 0, arr.length - 1);
    }

    /**
     * 递归执行排序操作，最坏的情况：空间复杂度是 O(n)
     *
     * @param arr
     * @param left
     * @param right
     */
    private static void execSortV1(int[] arr, int left, int right) {
        if (left >= right)
            return;
        int index = partitionV2(arr, left, right);
        //按基准点的位置，将数组拆分为两个子序列再进行排序
        execSortV1(arr, left, index - 1);
        execSortV1(arr, index + 1, right);
    }

    /**
     * 优化空间复杂度
     * <p>
     * 使用尾递归优化，分割成两个序列时，只对其中一个递归进去，
     * 另一个序列仍可以在这一函数内继续划分，可以显著减小栈的大小
     * 最坏的情况空间复杂度仍然可能为 O(n)
     *
     * @param arr
     * @param left
     * @param right
     */
    private static void execSortV2(int[] arr, int left, int right) {
        while (left < right) {
            int index = partitionV2(arr, left, right);
            //按基准点的位置，将数组拆分为两个子序列再进行排序
            execSortV2(arr, left, index - 1);
            left = index + 1;
        }
    }

    /**
     * 优化空间复杂度
     * <p>
     * 在尾递归的基础上再进行优化，使得每次都对包含数组元素较少的那一个划分部分进行递归调用
     * 在最坏情况下，快速排序的空间复杂度就会降低到 O(logn)
     *
     * @param arr
     * @param left
     * @param right
     */
    private static void execSortV3(int[] arr, int left, int right) {
        while (left < right) {
            int index = partitionV2(arr, left, right);
            if (index - left < right - index) {
                execSortV3(arr, left, index - 1);
                left = index + 1;
            } else {
                execSortV3(arr, index + 1, right);
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
    private static int partitionV1(int[] arr, int left, int right) {
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
     * 该方法比partitionV1效率更高
     * 划分操作，先移动右指针，遇到大于基准点的元素，停止，交换左指针和右指针指向的元素；再移动左指针，遇到小于基准点的元素，停止，
     * 交换右指针和左指针指向的元素；循环，直到left>=right；最后修改左指针指向的元素为基准点
     *
     * @param arr
     * @param left
     * @param right
     * @return
     */
    private static int partitionV2(int[] arr, int left, int right) {
        int pivotKey = selectPivotKey(arr, left, right);
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
     * 中位数选择，取最左元素
     * <p>
     * 当数组有序或者近乎有序时，时间复杂度退化为O(n^2)，通常不推荐使用
     *
     * @param arr
     * @param left
     * @return
     */
    private static int leftPivotKey(int[] arr, int left) {
        return arr[left];
    }

    /**
     * 随机化基准点
     * <p>
     * 当数组有序或者近乎有序时，时间复杂度会退化为O(n^2)，采用随机随机化基准点可降低概率，
     * 但仍然有极低的概率退化为O(n^2)
     *
     * @param arr
     * @param left
     * @param right
     * @return
     */
    private static int randomPivotKey(int[] arr, int left, int right) {
        Random random = new Random(right - left);
        return arr[random.nextInt() + left];
    }

    /**
     * 使用三数取中算法确定基准点
     * <p>
     * 推荐做法
     *
     * @param arr
     * @param left
     * @param right
     * @return
     */
    private static int selectPivotKey(int[] arr, int left, int right) {
        int mid = left + (right - left) / 2;
        if (arr[mid] > arr[right]) {//三数中最大移动到右边
            SortUtil.swap(arr, mid, right);
        }
        if (arr[left] > arr[right]) {//三数中最大移动到右边
            SortUtil.swap(arr, left, right);
        }
        if (arr[mid] > arr[left]) {//三数中次大移动到左边
            SortUtil.swap(arr, mid, left);
        }
        return arr[left];
    }

    /**
     * 3路快排
     *
     * @param arr
     */
    public static void quickSort3Ways(int[] arr) {
        if (null == arr || arr.length < 2)
            return;
        quickSort3Ways(arr, 0, arr.length - 1);
    }

    /**
     * 当数组中含有大量重复元素时，会大大影响排序的性能。因此，在2路排序的基础上，采用3路快速排序算法进行优化
     * 3路快排有效解决了数组有序和有大量重复元素的排序问题
     *
     * @param arr
     * @param left
     * @param right
     */
    private static void quickSort3Ways(int[] arr, int left, int right) {
        if (right <= left) {
            return;
        }
        int leftTemp = left;
        int rightTemp = right;
        int i = left + 1;
        int pivotKey = arr[left];
        logger.info("--------------------------------------------");
        while (i <= rightTemp) {
            logger.info("arr:{},i:{},leftTemp:{},rightTemp:{},pivotKey:{}", Arrays.stream(arr).mapToObj(value -> String.valueOf(value))
                    .collect(Collectors.joining(",")), i, leftTemp, rightTemp, pivotKey);
            if (arr[i] < pivotKey) {//小于基准点的左移
                SortUtil.swap(arr, i++, leftTemp++);
            } else if (pivotKey < arr[i]) {//大于基准点的右移
                SortUtil.swap(arr, i, rightTemp--);
            } else {//跳过等值情况
                i++;
            }
            logger.info("arr:{},i:{},leftTemp:{},rightTemp:{}", Arrays.stream(arr).mapToObj(value -> String.valueOf(value))
                    .collect(Collectors.joining(",")), i, leftTemp, rightTemp);
        }
        quickSort3Ways(arr, left, leftTemp - 1);
        quickSort3Ways(arr, rightTemp + 1, right);
    }
}
