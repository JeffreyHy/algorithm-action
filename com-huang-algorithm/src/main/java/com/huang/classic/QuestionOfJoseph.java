package com.huang.classic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 约瑟夫问题是一个非常著名的趣题，由m个人围成一个首尾相连的圈报数。从第一个人开始，从1开始报数，报到n的人出圈，
 * 剩下的人继续从1开始报数。现在需要求的是最后一个出局的人的编号。
 * 对于给定的m和n，求出所有人的出圈顺序。
 *
 * @author JeffreyHy
 * @date Created by  2018/2/24 16:41
 */
public class QuestionOfJoseph {
    private static final Logger logger = LoggerFactory.getLogger(QuestionOfJoseph.class);

    /**
     * 算法实现
     *
     * @param m 总的人数
     * @param n 出局者的编号
     */
    public static void joseph(int m, int n) {
        int a[] = new int[m];
        for (int i = 0; i < a.length; i++) {
            a[i] = i + 1;
        }
        int length = m;
        int i = 0;
        int j = 1;
        StringBuilder builder = new StringBuilder("result:");
        while (length > 0) {
            if (a[i % m] > 0) {
                if (j % n == 0) {
                    builder.append(a[i % m]).append(",");
                    a[i % m] = -1;
                    j = 1;
                    i++;
                    length--;
                } else {
                    i++;
                    j++;
                }
            } else {
                i++;
            }
        }
        logger.info(builder.substring(0, builder.length() - 1));
    }
}
