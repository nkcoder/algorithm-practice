package io.nkcoder.alg.basic.sorting;

/**
 * 插入排序
 * 思路：假设当前序列有序，插入一个新元素时，将新元素作为序列的最后一个元素，调整使整个序列有序，
 * 从后向前遍历序列，如果相邻的两个元素不是有序的，则互换使其有序，一趟遍历，新序列也是有序的。初始
 * 状态，有序序列为空。
 * 复杂度：时间O(n^2), 空间：O(1)；
 *
 * 参考：
 *  http://algs4.cs.princeton.edu/21elementary/Insertion.java.html
 *  http://algs4.cs.princeton.edu/21elementary/
 *
 * User: lingguo
 * Date: 14-7-25 上午7:34
 */
public class InsertionSort {

    /**
     * 遍历数组，调整顺序
     *
     * @param data
     */
    public static void sort(int[] data) {
        int end = data.length - 1;
        for (int i = 1; i <= end; i++) {
            for (int j = i; j > 0 && data[j] < data[j-1]; j--) {
                int tmp = data[j];
                data[j] = data[j-1];
                data[j-1] = tmp;
            }
        }
    }
}
