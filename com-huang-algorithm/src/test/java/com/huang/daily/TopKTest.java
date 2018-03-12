package com.huang.daily;

import com.huang.datastructure.Heap;
import junit.framework.TestCase;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author JeffreyHy
 * @date Created by  2018/3/7 16:32
 */
public class TopKTest extends TestCase {
    private static final Logger logger = LoggerFactory.getLogger(TopKTest.class);

    @Test
    public void testFindKMax_1() {
        int k = 4;
        int array[] = {20, 100, 4, 2, 87, 9, 8, 5, 46, 26};
        TopK.findTopKMax_1(array, 0, array.length - 1, k);
        logger.info("array top k:{}", Arrays.stream(array).mapToObj(value -> String.valueOf(value))
                .limit(k).collect(Collectors.joining(",")));
        int array1[] = {100, 90, 80, 70, 60, 50, 40, 30, 20, 10};//降序数组
        TopK.findTopKMax_1(array1, 0, array1.length - 1, k);
        logger.info("array1 top k:{}", Arrays.stream(array1).mapToObj(value -> String.valueOf(value))
                .limit(k).collect(Collectors.joining(",")));
        int array2[] = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100};//升序数组
        TopK.findTopKMax_1(array2, 0, array2.length - 1, k);
        logger.info("array2 top k:{}", Arrays.stream(array2).mapToObj(value -> String.valueOf(value))
                .limit(k).collect(Collectors.joining(",")));
    }

    @Test
    public void testFindKMin_1() {
        int k = 4;
        int array[] = {20, 100, 4, 2, 87, 9, 8, 5, 46, 26};
        int topK1[] = TopK.findTopKMin_1(array, k);
        logger.info("array top k:{}", Arrays.stream(topK1).mapToObj(value -> String.valueOf(value))
                .collect(Collectors.joining(",")));
        int array1[] = {100, 90, 80, 70, 60, 50, 40, 30, 20, 10};//降序数组
        int topK2[] = TopK.findTopKMin_1(array1, k);
        logger.info("array1 top k:{}", Arrays.stream(topK2).mapToObj(value -> String.valueOf(value))
                .collect(Collectors.joining(",")));
        int array2[] = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100};//升序数组
        int topK3[] = TopK.findTopKMin_1(array2, k);
        logger.info("array2 top k:{}", Arrays.stream(topK3).mapToObj(value -> String.valueOf(value))
                .collect(Collectors.joining(",")));
    }

    @Test
    public void testFindKMax_2() {
        int k = 4;
        int array[] = {20, 100, 4, 2, 87, 9, 8, 5, 46, 26};
        int topK1[] = TopK.findTopKMax_2(array, k);
        logger.info("array top k:{}", Arrays.stream(topK1).mapToObj(value -> String.valueOf(value))
                .collect(Collectors.joining(",")));
        int array1[] = {100, 90, 80, 70, 60, 50, 40, 30, 20, 10};//降序数组
        int topK2[] = TopK.findTopKMax_2(array1, k);
        logger.info("array1 top k:{}", Arrays.stream(topK2).mapToObj(value -> String.valueOf(value))
                .collect(Collectors.joining(",")));
        int array2[] = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100};//升序数组
        int topK3[] = TopK.findTopKMax_2(array2, k);
        logger.info("array2 top k:{}", Arrays.stream(topK3).mapToObj(value -> String.valueOf(value))
                .collect(Collectors.joining(",")));
    }

    @Test
    public void testFindKMax_3() {
        int k = 4;
        int array[] = {20, 100, 4, 2, 87, 9, 8, 5, 46, 26};
        List<Integer> topK = TopK.findTopKMax_3(array, k);
        logger.info("array top k:{}", topK.stream().map(value -> String.valueOf(value))
                .collect(Collectors.joining(",")));
        int array1[] = {100, 90, 80, 70, 60, 50, 40, 30, 20, 10};//降序数组
        topK = TopK.findTopKMax_3(array1, k);
        logger.info("array top k:{}", topK.stream().map(value -> String.valueOf(value))
                .collect(Collectors.joining(",")));
        int array2[] = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100};//升序数组
        topK = TopK.findTopKMax_3(array2, k);
        logger.info("array top k:{}", topK.stream().map(value -> String.valueOf(value))
                .collect(Collectors.joining(",")));
    }

    @Test
    public void testFindKMax_4() {
        int k = 4;
        int array[] = {20, 100, 4, 2, 87, 9, 8, 5, 46, 26};
        List<Integer> topK = TopK.findTopKMax_4(array, k);
        logger.info("array top k:{}", topK.stream().map(value -> String.valueOf(value))
                .collect(Collectors.joining(",")));
        int array1[] = {100, 90, 80, 70, 60, 50, 40, 30, 20, 10};//降序数组
        topK = TopK.findTopKMax_4(array1, k);
        logger.info("array top k:{}", topK.stream().map(value -> String.valueOf(value))
                .collect(Collectors.joining(",")));
        int array2[] = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100};//升序数组
        topK = TopK.findTopKMax_4(array2, k);
        logger.info("array top k:{}", topK.stream().map(value -> String.valueOf(value))
                .collect(Collectors.joining(",")));
    }

    @Test
    public void testBuildMaxHeap() {
        int array[] = {20, 100, 4, 2, 87, 9, 8, 5, 46, 26};
        Heap.buildMaxHeap(array);
        logger.info("heap:{}", Arrays.stream(array).mapToObj(value -> String.valueOf(value))
                .collect(Collectors.joining(",")));
    }
}
