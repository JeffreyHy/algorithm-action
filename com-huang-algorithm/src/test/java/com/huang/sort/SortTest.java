package com.huang.sort;

import junit.framework.TestCase;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author JeffreyHy
 * @date Created by  2018/2/24 15:23
 */
public class SortTest extends TestCase {
    private static final Logger logger = LoggerFactory.getLogger(TestCase.class);

    @Test
    public void testBubbleSort() {
        int arr[] = {5, 3, 6, 8, 9, 2};
        BubbleSort.sort(arr);
        StringBuilder builder = new StringBuilder("sort result:");
        for (int i : arr) {
            builder.append(i).append(",");
        }
        logger.info(builder.substring(0, builder.length() - 1));
    }
}
