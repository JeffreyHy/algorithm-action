package com.huang.daily;

import com.huang.search.BinarySearch;
import com.huang.util.ArraysUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * 问题描述：设L=<a1,a2,…,an>是n个不同的实数的序列，L的递增子序列是这样一个子序列Lin=<aK1,ak2,…,akm>，其中k1<k2<…<km且aK1<ak2<…<akm。求最大的m值。
 * 如在序列10，4，5，12，8中，最长递增子序列为4，5，12
 * <p>
 * 问题延伸：小猴子下山，沿着下山的路有一排桃树，每棵树都结了一些桃子。小猴子想摘桃子，
 * 但是又一些条件需要遵守，小瘦子只能沿着下山的方向走，不能回头，每棵树最多摘一个，
 * 而且一旦摘了一棵树的桃子，就不能再摘比这棵树结的桃子少的树上的桃子，那么小猴子最多能摘到几课桃子呢？
 *
 * @author JeffreyHy
 * @date Created by  2018/3/5 16:08
 */
public final class LongestIncreasingSubSequence {
    private static final Logger logger = LoggerFactory.getLogger(LongestIncreasingSubSequence.class);

    private LongestIncreasingSubSequence() {
    }

    /**
     * 动态规划解法，时间复杂度O（N^2 + N）=O（N^2）
     * 算法思路->
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
        int max = ArraysUtils.max(subSeq);
        logger.info("The longest increasing subSequence:{}", max);
        return max;
    }

    /**
     * 对于解法1，当考虑第i+1个元素时，我们没有不考虑前面第i个元素的分布情况。为了提高算法效率，现在我们换个思路：
     * 对于前面i个元素的任何一个递增子序列，如果这个子序列的最大元素比array[i+1]小，
     * 那么array[i+1]就可以加到这个子序列之后，构成新的递增子序列。
     * <p>
     * 基于此，我们寻找前i个元素的一个递增子序列，使得这个递增子序列的最大元素比array[i+1]小，
     * 且这个递增子序列的长度尽量地长，这样将array[i+1]加到该子序列后，便可以找到以array[i+1]为最大元素的最长递增自序列。
     * <p>
     * 假设数组前i个元素中，以array[i]为最大元素的最长递增子序列的长度为LIS[i],同时假设：
     * 长度为1的递增子序列的最小末端元素为maxV[1];
     * 长度为2的递增子序列的最小末端元素为maxV[2];
     * ......
     * 长度为i的递增子序列的最小末端元素为maxV[LIS[i]].
     * 通过维护这些数据，我们可以算法中利用相关信息来减少判断的次数。（末端即最大元素）
     * 算法的关键点：当产生长度相同递增子序列时，需要选择最小末端对应的子序列，
     * 末端比较大的子序列被抛弃，这很好理解，向后遍历源数组是，更小末端更有可能产生长度更长的递增子序列
     * <p>
     * 时间复杂度O（N^2）
     *
     * @param array
     * @return
     */
    public static int getSubSeq_2(int[] array) {
        //记录数组的递增序列信息，下标为递增序列长度，value为对应长度的最小末端
        //最小末端：随着向后遍历数组，指定长度可能出现最更小的末端元素，这时候需要替换掉当前末端
        int[] maxV = new int[array.length + 1];
        maxV[0] = ArraysUtils.min(array) - 1;//数组中最小值，边界
        maxV[1] = array[0];//数组中第一个值，暂定为长度为1的最小末端
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
            } else if (maxV[j] < array[i] && array[i] < maxV[j + 1]) {//更新长度j+1的最小末端
                maxV[j + 1] = array[i];
            } /*else if (subSeq[i] == maxSubSeq && maxV[maxSubSeq] > array[i]) { //只更新最大长度的最小末端
                maxV[maxSubSeq] = array[i];
            }*/
        }
        logger.info("The longest increasing subSequence:{}", maxSubSeq);
        logger.info("maxV:{}", Arrays.stream(maxV).mapToObj(value -> String.valueOf(value))
                .skip(1).collect(Collectors.joining(",")));
        logger.info("subSeq:{}", Arrays.stream(subSeq).mapToObj(value -> String.valueOf(value))
                .collect(Collectors.joining(",")));
        return maxSubSeq;
    }

    /**
     * maxV中的数据改为二分查找
     * 时间复杂度O（N * logn）
     *
     * @param array
     * @return
     */
    public static int getSubSeq_3(int[] array) {
        int[] maxV = new int[array.length + 1];
        maxV[1] = array[0];//数组中第一个值，暂定为长度为1的最小末端
        int maxSubSeq = 1;//数组最长递增子序列的长度
        int pos;
        for (int i = 1; i < array.length; i++) {
            pos = BinarySearch.biSearch(maxV, 1, maxSubSeq, array[i]);
            maxV[pos] = array[i];
            if (maxSubSeq < pos) {
                maxSubSeq = pos;
            }
        }
        logger.info("The longest increasing subSequence:{}", maxSubSeq);
        return maxSubSeq;
    }

}