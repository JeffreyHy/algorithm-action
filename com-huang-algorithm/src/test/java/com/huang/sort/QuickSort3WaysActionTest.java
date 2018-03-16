package com.huang.sort;

import junit.framework.TestCase;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author JeffreyHy
 * @date Created by  2018/3/16 18:01
 */
public class QuickSort3WaysActionTest extends TestCase {
    private static final Logger logger = LoggerFactory.getLogger(QuickSort3WaysActionTest.class);

    @Test
    public void test() {
        int arr[] = {1, 0, 1, 2, 0, 2};
        QuickSort3WaysAction.countSort(arr);
        logger.info("countSort:{}", Arrays.stream(arr).mapToObj(value -> String.valueOf(value))
                .collect(Collectors.joining(",")));

        int arr1[] = {1, 0, 1, 2, 0, 2};
        QuickSort3WaysAction.quickSort3Ways(arr1);
        logger.info("countSort:{}", Arrays.stream(arr1).mapToObj(value -> String.valueOf(value))
                .collect(Collectors.joining(",")));
    }
}
