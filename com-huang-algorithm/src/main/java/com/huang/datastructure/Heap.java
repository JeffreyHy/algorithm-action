package com.huang.datastructure;

import com.huang.util.SortUtil;

/**
 * n个关键字序列Kl，K2，…，Kn称为（Heap），当且仅当该序列满足如下性质：
 * 小根堆：ki<=k(2i）且ki<=k(2i+1)(1<= i <= n/2，ki代表非叶子节点，K(2i）是左子节点，k(2i+1）是右子节点
 * 大根堆：ki>=k(2i）且ki>=k(2i+1)(1<= i <= n/2）
 * <p>
 * 大根堆和小根堆：根结点（亦称为堆顶）的关键字是堆里所有结点关键字中最小者的堆称为小根堆，又称最小堆。
 * 根结点（亦称为堆顶）的关键字是堆里所有结点关键字中最大者，称为大根堆，又称最大堆。
 * <p>
 * 堆是一棵完全二叉树
 *
 * @author JeffreyHy
 * @date Created by  2018/3/8 14:35
 */
public final class Heap {
    private Heap() {
    }

    /**
     * 构建大根堆
     *
     * @param array
     */
    public static void buildMaxHeap(int[] array) {
        for (int i = array.length / 2 - 1; i >= 0; i--) {
            adjustMaxHeap(array, i, array.length);
        }
    }

    /**
     * 数据：20, 100, 4, 2, 87, 9, 8, 5, 46, 26
     * 下标：0 , 1 ,  2, 3, 4 , 5, 6, 7, 8 , 9
     * 数组表示为二叉堆：
     * ------------------20--------------
     * ------------100----------4--------
     * ---------2-------87---9-----8-----
     * ------5----46--26-----------------
     * 构建大根堆，第一轮：
     * root = i = array.length / 2 - 1 = （4，87）
     * left = （9，26） ,right = 10
     * right<length不满足，root索引对应的元素最大，不用调整，结果：
     * 第二轮：root = （3，2）
     * left = （7，5） ,right = （8，46）
     * 最大元素为right对应的元素46，root元素交换为46，以索引8为root进入递归，left<length,
     * right < length不满足，退出，结果：
     * ------------------20--------------
     * ------------100----------4--------
     * ---------46-----87---9-----8-----
     * ------5----2--26-----------------
     * 第三轮：root=（2，4）
     * left = （5，9） ,right = （6，8）
     * 最大元素为left对应的元素9,root元素交换为9，以索引5为root进入递归，left<length,
     * right < length不满足，退出，结果：
     * ------------------20--------------
     * ------------100---------9--------
     * ---------46-----87---4-----8-----
     * ------5----2--26-----------------
     * 第四轮：root=（1，100）
     * left = （3，46） ,right = （4，87）
     * 无需调整
     * 第五轮：root=（0，20）
     * left = （1，100） ,right = （2，9）
     * 最大元素为left对应的元素100，root元素交换为100，结果：
     * -----------------100------------
     * -------------20--------9--------
     * ---------46-----87---4-----8-----
     * ------5----2--26-----------------
     * 以索引1为root进入递归：
     * root=（1，20），left = （3，46） ,right = （4，87）
     * 最大元素为right对应的元素87，root元素交换为87，结果：
     * -----------------100------------
     * -------------87--------9--------
     * ---------46-----20---4-----8-----
     * ------5----2--26-----------------
     * 以索引4为root进入递归：
     * root = （4，20），left = （9，26） ,right = 10
     * 最大元素为left对应的元素26，root元素交换为26，结果：
     * -----------------100------------
     * -------------87--------9--------
     * ---------46-----26---4-----8-----
     * ------5----2--20-----------------
     * 以索引9为root进入递归，left<length,right < length不满足，退出。到此，大顶堆构建完成。
     *
     * @param array
     * @param root   根节点
     * @param length
     */
    public static void adjustMaxHeap(int[] array, int root, int length) {
        int left = root * 2 + 1; //左节点下标，数组下标从0开始，所以加1
        int right = left + 1; //右节点下标
        int largest = root;// 存放三个节点中最大节点的下标
        if (left < length && array[left] > array[root]) { //左节点大于根节点，更新最大节点的下标
            largest = left;
        }
        if (right < length && array[right] > array[largest]) {//右节点大于根节点，最大节点的下标
            largest = right;
        }
        if (root != largest) {
            SortUtil.swap(array, largest, root);
            adjustMaxHeap(array, largest, length);
        }
    }

    /**
     * 构建小根堆
     *
     * @param array
     */
    public static void buildMinHeap(int[] array) {
        for (int i = array.length / 2 - 1; i >= 0; i--) {
            adjustMinHeap(array, i, array.length);
        }
    }

    /**
     * 小根堆
     *
     * @param array
     * @param root
     * @param length
     */
    public static void adjustMinHeap(int[] array, int root, int length) {
        int left = root * 2 + 1; //左节点下标
        int right = left + 1; //右节点下标
        int smallest = root;// 存放三个节点中最小节点的下标
        if (left < length && array[left] < array[root]) {//左节点小于根节点，更新最小节点的下标
            smallest = left;
        }
        if (right < length && array[right] < array[smallest]) {//右节点小于根节点，更新最小节点的下标
            smallest = right;
        }
        if (root != smallest) {
            SortUtil.swap(array, smallest, root);
            adjustMinHeap(array, smallest, length);//调整smallest与左右子节点，以满足堆性质
        }
    }
}
