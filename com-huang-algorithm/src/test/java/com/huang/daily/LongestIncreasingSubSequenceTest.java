package com.huang.daily;

import junit.framework.TestCase;
import org.junit.Test;

/**
 * @author JeffreyHy
 * @date Created by  2018/3/6 18:06
 */
public class LongestIncreasingSubSequenceTest  extends TestCase {
    @Test
    public void test(){
        int[] array = {10, 4, 5, 12, 8};
        int subSeq1=LongestIncreasingSubSequence.getSubSeq_1(array);
        int subSeq2=LongestIncreasingSubSequence.getSubSeq_2(array);
        int subSeq3=LongestIncreasingSubSequence.getSubSeq_3(array);
        assertEquals(subSeq1,subSeq2);
        assertEquals(subSeq3,subSeq2);
        int[] array1 = {1, -1, 2, -3, 4, -5, 6, -7};
        LongestIncreasingSubSequence.getSubSeq_1(array1);
        LongestIncreasingSubSequence.getSubSeq_2(array1);
        LongestIncreasingSubSequence.getSubSeq_3(array1);
        int[] array2 = {-1, -3, 2, 0, 1};
        LongestIncreasingSubSequence.getSubSeq_1(array2);
        LongestIncreasingSubSequence.getSubSeq_2(array2);
        LongestIncreasingSubSequence.getSubSeq_3(array2);
    }
}
