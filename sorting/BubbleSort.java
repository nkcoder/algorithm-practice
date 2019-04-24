package io.nkcoder.alg.basic.sorting;

/**
 * 冒泡排序：(升序)
 *  思路：持续比较相邻元素, 大的移到后面;
 *
 * *优化1*：如果某一趟遍历中，没有发生元素交换，则表示已排好序，提前结束。
 *
 * *优化2*：在每一趟遍历中，记录最后一次交换的位置，作为下一次交换的起点, 该优化可以减少比较的次数。
 *
 * *复杂度*: 时间复杂度O(N^2), 空间复杂度O(1)
 *
 * User: lingguo
 * Date: 14-7-26 下午2:38
 */
public class BubbleSort {

    /**
     * 优化1: 如果某一趟遍历中，没有发生元素交换，则表示已排好序，提前结束。
     *
     * @param data
     */
    public static void sort(int[] data) {
        int n = data.length - 1;
        boolean swapped = true;
        // i表示本趟遍历结束，data[i]为当前最大值
        for (int i = n; i > 0 && swapped; i--) {
            swapped = false;
            // 两两比较，最小值“沉”到最后
            for (int j = 1; j <= i; j++) {
                if (data[j-1] > data[j]) {
                    int tmp = data[j-1];
                    data[j-1] = data[j];
                    data[j] = tmp;
                    swapped = true;
                }
            }
        }
    }

    /**
     * 优化2：在每一趟遍历中，记录最后一次交换的位置，作为下一次交换的起点, 该优化可以减少比较的次数。
     *
     * @param data
     */
    public static void sortX(int[] data) {
        int n = data.length - 1;
        while (n != 0) {
            int lastSwap = 0;
            for (int i = 1; i <= n; i++) {
                if (data[i-1] > data[i]) {
                    int tmp = data[i-1];
                    data[i-1] = data[i];
                    data[i] = tmp;
                    lastSwap = i;              // 记录最后发生交换的位置
                }
            }
            n = lastSwap;
        }
    }
}
