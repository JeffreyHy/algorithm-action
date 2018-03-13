package com.huang.daily;

import junit.framework.TestCase;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author JeffreyHy
 * @date Created by  2018/3/13 10:17
 */
public class SubArrayMaxSumTest extends TestCase {
    private static final Logger logger = LoggerFactory.getLogger(SubArrayMaxSumTest.class);

    @Test
    public void testGetMaxSumV1() {
        int array[] = {3, 4, 5, -2, 1, 6};
        logger.info("{}", SubArrayMaxSum.getMaxSumV1(array));
        int array1[] = {3, 4, -5, -2, 1, -6};
        logger.info("{}", SubArrayMaxSum.getMaxSumV1(array1));
    }

    @Test
    public void testGetMaxSumV2() {
        int array[] = {3, 4, 5, -2, 1, 6};
        logger.info("{}", SubArrayMaxSum.getMaxSumV2(array));
        int array1[] = {3, 4, -5, -2, 1, -6};
        logger.info("{}", SubArrayMaxSum.getMaxSumV2(array1));
    }

    @Test
    public void testGetMaxSumV3() {
        int array[] = {3, 4, 5, -2, 1, 6};
        logger.info("{}", SubArrayMaxSum.getMaxSumV3(array, 0, array.length - 1));
        int array1[] = {3, 4, -5, -2, 1, -6};
        logger.info("{}", SubArrayMaxSum.getMaxSumV3(array1, 0, array1.length - 1));
    }

    @Test
    public void testGetMaxSumV4() {
        int array[] = {3, 4, 5, -2, 1, 6};
        logger.info("{}", SubArrayMaxSum.getMaxSumV4(array, array.length));
        int array1[] = {3, 4, -5, -2, 1, -6};
        logger.info("{}", SubArrayMaxSum.getMaxSumV4(array1, array1.length));
        int array2[] = {-3, -4, -5, -2, -1, -6};
        logger.info("{}", SubArrayMaxSum.getMaxSumV4(array2, array2.length));
    }

    @Test
    public void testGetMaxSumV5() {
        int array[] = {3, 4, 5, -2, 1, 6};
        logger.info("{}", SubArrayMaxSum.getMaxSumV5(array, array.length));
        int array1[] = {3, 4, -5, -2, 1, -6};
        logger.info("{}", SubArrayMaxSum.getMaxSumV5(array1, array1.length));
        int array2[] = {-3, -4, -5, -2, -1, -6};
        logger.info("{}", SubArrayMaxSum.getMaxSumV5(array2, array2.length));
    }
}
