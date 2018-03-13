package com.huang.daily;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 一个有N个整数元素的一维数组（A[0],A[1]...A[n-2],A[n-1]）,这个数组有很多子数组，求子数组之和的最大值。
 * 审题：
 * 1 子数组是连续的
 * 2 只要求求和，不需要返回子数组的具体位置
 * 3 数组的元素是整数，即数组可能包含正整数、零、负整数
 *
 * @author JeffreyHy
 * @date Created by  2018/3/13 10:08
 */
public final class SubArrayMaxSum {
    private static final Logger logger = LoggerFactory.getLogger(SubArrayMaxSum.class);

    private SubArrayMaxSum() {
    }

    /**
     * 最直接的算法，记sum[i,...,j]为数组A中第i个元素到第j个元素的和，其中0<=i<=j<array.length，
     * 遍历所有可能的sum[i,...,j]，时间复杂度为O(N^3)
     *
     * @param array
     * @return
     */
    public static Integer getMaxSumV1(int array[]) {
        if (array == null || array.length <= 0) {
            return null;
        }
        int maximum = Integer.MIN_VALUE;
        int sum;
        for (int i = 0; i < array.length; i++) {
            for (int j = i; j < array.length; j++) {
                sum = 0;
                for (int k = i; k <= j; k++) {
                    sum += array[k];
                }
                if (sum > maximum) {
                    maximum = sum;
                }
            }
        }
        return maximum;
    }

    /**
     * 注意观察可知sum[i,...,j]=sum[i,...,j-1]+array[j],这样我们就可以省略一个for循环，
     * 避免重复计算，时间复杂度降为O(N^2)
     *
     * @param array
     * @return
     */
    public static Integer getMaxSumV2(int array[]) {
        if (array == null || array.length <= 0) {
            return null;
        }
        int maximum = Integer.MIN_VALUE;
        int sum;
        for (int i = 0; i < array.length; i++) {
            sum = 0;
            for (int j = i; j < array.length; j++) {
                sum += array[j];
                if (sum > maximum) {
                    maximum = sum;
                }
            }
        }
        return maximum;
    }

    /**
     * 递归分治解法
     * 分析：尝试将数组（A[0],A[1]...A[n-2],A[n-1]）分为长度相等的两段数组，（A[0],...,A[n/2-1]）和（A[n/2],...,A[n-1]）,
     * 分别求出这两段数组各自的最大子段和，则原数组（A[0],A[1]...A[n-2],A[n-1]）的最大子段和为以下三种情况的最大值：
     * 1 （A[0],A[1]...A[n-2],A[n-1]）的最大子段和在（A[0],...,A[n/2-1]）中求得
     * 2 （A[0],A[1]...A[n-2],A[n-1]）的最大子段和在（A[n/2],...,A[n-1]）中求得
     * 3 （A[0],A[1]...A[n-2],A[n-1]）的最大子段和跨过其中间两个元素A[n/2-1]到A[n/2]
     * 1,2两种情况是问题规模减半的子问题，可以通过递归求得。
     * 第3种情况，只需要找到以A[n/2-1]结尾的和最大的一段数组和s1=（A[i],...,A[n/2-1]）（0<=i<n/2-1）和以A[n/2]开始的
     * 最大的一段数组和s2=（A[n/2],...,A[j]）（n/2<=j<n。最终第3种情况的最大值为s1+s2=array[i]+..+array[n/2-1]+array[n/2]+array[j]，
     * 我们只需要对原数组遍历一次即可。
     * <p>
     * 这是一种分治算法，将问题都分解为两个规模减半的子问题，再加上一次遍历算法。总的时间复杂度为O(N * logN)
     *
     * @param array
     * @param left
     * @param right
     * @return
     */
    public static Integer getMaxSumV3(int array[], int left, int right) {
        if (array == null || array.length <= 0) {
            return null;
        }
        if (left == right) {
            return array[left];
        }
        int mid = (left + right) / 2;
        int leftSum = getMaxSumV3(array, left, mid);//递归求解左段数组的子数组最大和
        int rightSum = getMaxSumV3(array, mid + 1, right);//递归求解左段数组的子数组最大和
        int crossSum = getCrossingMaxSum(array, left, right, mid);//求跨越中点的子数组最大和
        if (leftSum >= rightSum && leftSum >= crossSum) {
            return leftSum;
        } else if (rightSum >= leftSum && rightSum >= crossSum) {
            return rightSum;
        } else {
            return crossSum;
        }
    }

    /**
     * 求跨越中点的子数组最大和
     *
     * @param array
     * @param left
     * @param right
     * @param mid
     * @return
     */
    private static int getCrossingMaxSum(int array[], int left, int right, int mid) {
        int leftSum = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = mid; i >= left; i--) {
            sum += array[i];
            if (sum > leftSum) {
                leftSum = sum;
            }
        }
        int rightSum = Integer.MIN_VALUE;
        sum = 0;
        for (int i = mid + 1; i <= right; i++) {
            sum += array[i];
            if (sum > rightSum) {
                rightSum = sum;
            }
        }
        return leftSum + rightSum;
    }

    /**
     * 动态规划解法
     * 分析：首先看array[0]和最大的一段数组（A[i],...,A[j]）之间的关系：
     * 1 当0=i=j时，元素array[0]本身即为数组最大子数组和
     * 2 当0=i<j，最大和子数组以array[0]开始
     * 2 当0<i时，元素array[0]和最大和子数组无关
     * 基于此，可以将一个大问题（N个元素数组）转化为一个较小的问题（N-1个元素的数组）。
     * 假设已经知道（A[1],...,A[N-1]）中最大的子数组和为maxSum[1],并且已经知道（A[1],...,A[N-1]）中
     * 包含array[1]的最大子数组和为start[1]。那么，根据上述分析的三种情况可知，（A[0],...,A[N-1]）的
     * 最大子数组和maxSum[0]为array[0],array[0]+start[1],maxSum[1]三者中的最大值。经分析，该问题符合
     * 无后效性，因此可采用动态规划的方法来解决。
     * <p>
     * 总的时间复杂度为O(N)
     *
     * @param array
     * @param length
     * @return
     */
    public static Integer getMaxSumV4(int array[], int length) {
        if (array == null || array.length <= 0) {
            return null;
        }
        int start[] = new int[length];
        int maxSum[] = new int[length];
        start[length - 1] = array[length - 1];
        maxSum[length - 1] = array[length - 1];
        if (length > 1) {
            for (int i = length - 2; i >= 0; i--) {//从数组末尾向前遍历
                start[i] = max(array[i], array[i] + start[i + 1]);
                maxSum[i] = max(start[i], maxSum[i + 1]);
            }
        }
        return maxSum[0];
    }

    /**
     * 求两者中最大值
     *
     * @param x
     * @param y
     * @return
     */
    private static int max(int x, int y) {
        return x > y ? x : y;
    }

    /**
     * 动态规划解法优化
     * 从上面的实现可知，虽然算法的时间复杂度已经降到O(N)了，但额外申请了两个数组start[]、maxSum[]，
     * 空间复杂度变为O(N)了。
     * 我们可以进一步优化算法的空间复杂度，观察两个递推式：
     * start[i] = max(array[i], array[i] + start[i + 1]);maxSum[i] = max(start[i], maxSum[i + 1]);
     * 实际只需要两个变量来临时存储start[i + 1]和maxSum[i + 1]，从而使空间复杂度降为O(1)
     *
     * @param array
     * @param length
     * @return
     */
    public static Integer getMaxSumV5(int array[], int length) {
        if (array == null || length <= 0) {
            return null;
        }
        int start = array[length - 1];
        int maxSum = array[length - 1];
        if (length > 1) {
            for (int i = length - 2; i >= 0; i--) {//从数组末尾向前遍历
                start = max(array[i], array[i] + start);
                maxSum = max(start, maxSum);
            }
        }
        return maxSum;
    }
}
