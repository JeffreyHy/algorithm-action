package com.huang.daily;

/**
 * 问题描述：求一个数组（N个元素）中的最长递增子序列，如在序列10，4，5，12，8中，最长递增子序列为4，5，12
 * <p>
 * 问题延伸：小猴子下山，沿着下山的路有一排桃树，每棵树都结了一些桃子。小猴子想摘桃子，
 * 但是又一些条件需要遵守，小瘦子只能沿着下山的方向走，不能回头，每棵树最多摘一个，
 * 而且一旦摘了一棵树的桃子，就不能再摘比这棵树结的桃子少的树上的桃子，那么小猴子最多能摘到几课桃子呢？
 *
 * @author JeffreyHy
 * @date Created by  2018/3/5 16:08
 */
public class LongestIncrementSubSequence {
    public static void main(String[] args) {
        int[] array = {10, 4, 5, 12, 8};
        System.out.println(getSubSeq_1(array));
        int [] array1={1,-1,2,-3,4,-5,6,-7};
        System.out.println(getSubSeq_2(array1));
    }

    /**
     * 动态规划解法，时间复杂度O（N * N + N）=O（N * N）
     * 算法思路：
     * i代表当前遍历的位置：
     * 当i=1时，最长的递增序列为{10}
     * 当i=2时，由于4<10，因此必须丢弃第一个值然后重新立串。当前的递增序列为{4}
     * 当i=3时，由于5>4。因此，最长的递增序列为{4,5}，长度为2
     * 以此类推，可以得出如下结论：
     * 假设目标数组array的前i个元素中，最长的递增子序列的长度为subSeq[i]。那么，
     * 对任意j<=i，subSeq[i+1]=max{1,subSeq[j]+1},array[i+1]>array[j]
     * 即如果array[i+1]>array[j]，那么第i+1个元素可以接在subSeq[j]长得子序列后面构成一个更长的子序列。
     * （array[i+1]本身至少可以构成一个长度为1的子序列）
     *
     * @param array
     * @return
     */
    public static int getSubSeq_1(int[] array) {
        int[] subSeq = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            subSeq[i] = 1; //初始化默认长度
            for (int j = 0; j < i; j++) { //寻找i前面最长的序列
                if (array[i] > array[j] && subSeq[j] + 1 > subSeq[i]) {
                    subSeq[i] = subSeq[j] + 1;
                }
            }
        }
        return max(subSeq);
    }

    /**
     * 求数组中最大值
     *
     * @param array
     * @return
     */
    private static int max(int[] array) {
        int max = array[0];
        for (int i = 0; i < array.length; i++) {
            if (max < array[i]) {
                max = array[i];
            }
        }
        return max;
    }

    /**
     * 求数组中最小值
     *
     * @param array
     * @return
     */
    private static int min(int[] array) {
        int min = array[0];
        for (int i = 0; i < array.length; i++) {
            if (min > array[i]) {
                min = array[i];
            }
        }
        return min;
    }

    /**
     * 时间复杂度O（N * N）
     *
     * @param array
     * @return
     */
    public static int getSubSeq_2(int[] array) {
        int[] maxV = new int[array.length + 1];
        maxV[0] = min(array) - 1;//数组中最小值，边界
        maxV[1] = array[0];//数组中第一个值，边界
        int subSeq[] = new int[array.length];
        for (int i = 0; i < subSeq.length; i++) {
            subSeq[i] = 1;
        }
        int maxSubSeq = 1;//数组最长递增子序列的长度
        for (int i = 1; i < array.length; i++) {
            int j = maxSubSeq;
            for (; j >= 0; j--) {
                if (array[i] > maxV[j]) {
                    subSeq[i] = j + 1;
                    break;
                }
            }
            if (subSeq[i] > maxSubSeq) {
                maxSubSeq = subSeq[i];
                maxV[maxSubSeq] = array[i];
            } else if (maxV[j] < array[i] && array[i] < maxV[j + 1]) {
                maxV[j + 1] = array[i];
            }
        }
        for (int i = 0; i <maxV.length ; i++) {
            System.out.println("maxV:"+maxV[i]);
        }
        return maxSubSeq;
    }
}