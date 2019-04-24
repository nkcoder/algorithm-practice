package io.nkcoder.alg.basic.sorting;


/**
 * 选择排序：
 *  思路：遍历一趟数组，比较选择最小的元素(按递增排序)，放在数组的第一个位置，然后遍历
 *  身下的n-1个元素，选择最小元素，放在数组的第二个位置，依此类推，直到数组有序。
 *  复杂度：时间O(n^2)，空间O(1)
 *
 * 参考：
 *  http://algs4.cs.princeton.edu/21elementary/Selection.java.html
 *
 * User: lingguo
 * Date: 14-7-25 下午10:27
 */
public class SelectionSort {

    /**
     * 选择排序
     *
     * @param data
     */
    public static void sort(int[] data) {
        int len = data.length;
        for (int i = 0; i < len; i++) {
            int min = i;
            for (int j = i + 1; j < len; j++) {
                if (data[j] < data[min]) {
                    min = j;
                }
            }
            int tmp = data[i];
            data[i] = data[min];
            data[min] = tmp;
        }
    }
}
