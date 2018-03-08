package com.huang.sort;

import junit.framework.TestCase;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author JeffreyHy
 * @date Created by  2018/2/24 15:23
 */
public class SortTest extends TestCase {
    private static final Logger logger = LoggerFactory.getLogger(TestCase.class);

    @Test
    public void testBubbleSort() {
        int arr[] = {5, 3, 6, 8, 9, 2};
        BubbleSort.ascendingOrder(arr);
        logger.info("ascendingOrder:{}", Arrays.stream(arr).mapToObj(value -> String.valueOf(value))
                .collect(Collectors.joining(",")));
        BubbleSort.descendingOrder(arr);
        logger.info("descendingOrder:{}", Arrays.stream(arr).mapToObj(value -> String.valueOf(value))
                .collect(Collectors.joining(",")));
    }

    @Test
    public void testInsertSort() {
        int arr[] = {5, 3, 6, 8, 9, 2};
        InsertSort.ascendingOrder(arr);
        logger.info("ascendingOrder:{}", Arrays.stream(arr).mapToObj(value -> String.valueOf(value))
                .collect(Collectors.joining(",")));
        InsertSort.descendingOrder(arr);
        logger.info("descendingOrder:{}", Arrays.stream(arr).mapToObj(value -> String.valueOf(value))
                .collect(Collectors.joining(",")));
    }

    @Test
    public void testHeapSort() {
        int arr[] = {5, 3, 6, 8, 9, 2};
        HeapSort.ascendingOrder(arr);
        logger.info("ascendingOrder:{}", Arrays.stream(arr).mapToObj(value -> String.valueOf(value))
                .collect(Collectors.joining(",")));
        HeapSort.descendingOrder(arr);
        logger.info("descendingOrder:{}", Arrays.stream(arr).mapToObj(value -> String.valueOf(value))
                .collect(Collectors.joining(",")));
    }
}
