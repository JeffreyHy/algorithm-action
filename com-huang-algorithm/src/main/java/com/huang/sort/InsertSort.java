package com.huang.sort;

/**
 * 插入排序：基本思想是每步将一个待排序的纪录，按其关键码值的大小插入前面已经排序的文件中适当位置上，直到全部插入完为止。
 * <p>
 * 如：对5,3,8,6,4这个无序序列进行简单插入排序，首先假设第一个数的位置时正确的，然后3要插到5前面，把5后移一位，
 * 变成3,5,8,6,4。然后8不用动，6插在8前面，8后移一位，4插在5前面，从5开始都向后移一位。注意在插入一个数的时候要保证这个数前面的数已经有序。
 * <p>
 * 简单插入排序的时间复杂度也是O(n^2)。
 *
 * @author JeffreyHy
 * @date Created by  2018/2/24 14:25
 */
public final class InsertSort {
    private InsertSort() {
    }

    public static void sort(int[] arr) {
        if (null == arr || arr.length < 0)
            return;
        for (int i = 1; i < arr.length; i++) {
            int j = i;
            int target = arr[i];//待插入的元素
            //移动元素，寻找插入位置
            while (j > 0 && target < arr[j - 1]) {
                arr[j] = arr[j - 1];
                j--;
            }
            //插入
            arr[j] = target;
        }
    }

}
