package com.huang.classic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 汉诺塔（又称河内塔）问题是源于印度一个古老传说的益智玩具。大梵天创造世界的时候做了三根金刚石柱子，
 * 在一根柱子上从下往上按照大小顺序摞着64片黄金圆盘。大梵天命令婆罗门把圆盘从下面开始按大小顺序重新摆放在另一根柱子上。
 * 并且规定，在小圆盘上不能放大圆盘，在三根柱子之间一次只能移动一个圆盘。
 * <p>
 * 抽象为数学问题：从左到右有A、B、C三根柱子，其中A柱子上面有从小叠到大的n个圆盘，现要求将A柱子上的圆盘移到C柱子上去，
 * 期间只有一个原则：一次只能移到一个盘子且大盘子不能在小盘子上面，求移动的步骤和移动的次数
 *
 * @author JeffreyHy
 * @date Created by  2018/2/24 16:27
 */
public class TowersOfHanoi {
    private static final Logger logger = LoggerFactory.getLogger(TowersOfHanoi.class);

    /**
     * 将n个盘子从x借助y移到z
     *
     * @param n 层数
     * @param x
     * @param y
     * @param z
     */
    public static void move(int n, char x, char y, char z) {
        if (1 == n) {
            logger.info("{}->{}", x, z);
        } else {
            //将n-1个盘子从x借助z移动到y
            move(n - 1, x, z, y);
            //将第n个盘子从x移到z
            logger.info("{}->{}", x, z);
            //将n-1个盘子从y借助x移到z
            move(n - 1, y, x, z);
        }
    }
}
