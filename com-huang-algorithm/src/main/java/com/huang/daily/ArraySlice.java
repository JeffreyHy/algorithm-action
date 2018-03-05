package com.huang.daily;

/**
 * 问题描述：一个整型数组，将其划分为和相同的4个切片，例如：{ 2, 3, 5, 1, 2, 2, 1, 1, 3 }，
 * 切片操作后划分为：{2,3}，{5}，{1,2,2}，{1,1,3}，也就找到所谓的四等分点。
 * 只不过输出结果为true或者false（是否能得到这样的4个切片）。同时要求时间复杂度和空间复杂度为o(n)
 *
 * @author JeffreyHy
 * @date Created by  2018/3/5 13:59
 */
public class ArraySlice {
    public static void main(String[] args) {
        int arr[] = {2, 3, 5, 1, 2, 2, 1, 1, 3};
        System.out.println(resolve(arr, 4));
        System.out.println(resolve(arr, 5));
    }

    /**
     * 分片算法
     *
     * @param arr
     * @param sliceCount
     * @return
     */
    static boolean resolve(int[] arr, int sliceCount) {
        // 第一步，对数组进行求和
        int sum = 0;
        for (int i : arr) {
            sum = sum + i;
        }
        // 确定是否可以化为4个切片
        int sliceSum = sum / sliceCount;
        if (sliceSum * sliceCount != sum) {
            return false;
        }
        // 用于保存每个切片的结束索引
        int[] sliceIndex = new int[sliceCount];
        int sliceCountTmp = 0;
        int tmpSum = 0;
        for (int i = 0; i < arr.length; i++) {
            tmpSum = tmpSum + arr[i];
            if (tmpSum == sliceSum) {
                sliceIndex[sliceCountTmp] = i;
                sliceCountTmp++;
                tmpSum = 0;
            }
        }
        if (sliceIndex[3] == arr.length - 1) {
            print(arr, sliceIndex);
            return true;
        }
        return false;
    }

    /**
     * 输出结果
     *
     * @param arr
     * @param sliceIndex
     */
    private static void print(int[] arr, int[] sliceIndex) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < sliceIndex.length; i++) {
            int j = 0;
            if (i > 0) {
                j = sliceIndex[i - 1] + 1;
            }
            builder.append("{");
            for (; j <= sliceIndex[i]; j++) {
                builder.append(arr[j]).append(",");
            }
            builder.deleteCharAt(builder.length() - 1).append("}").append("、");
        }
        System.out.println(builder.deleteCharAt(builder.length() - 1));
    }
}
