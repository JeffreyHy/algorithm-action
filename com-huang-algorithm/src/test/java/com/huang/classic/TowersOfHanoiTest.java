package com.huang.classic;

import junit.framework.TestCase;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author JeffreyHy
 * @date Created by  2018/2/24 16:34
 */
public class TowersOfHanoiTest extends TestCase {
    private static final Logger logger = LoggerFactory.getLogger(TowersOfHanoiTest.class);

    @Test
    public void testHanoi() {
        int n = 5;//汉诺塔的层数
        logger.info("move steps:");
        TowersOfHanoi.move(n, 'X', 'Y', 'Z');
    }

}
