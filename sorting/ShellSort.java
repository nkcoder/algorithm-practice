package io.nkcoder.alg.basic.sorting;



/**
 * 希尔排序：
 *  思路：基于插入排序, 使数组中任意间隔为h的元素都是有序的, 即将全部元素分为h个区域使用插入排序,
 *  其实现可类似与插入排序但使用不同增量; 更高效的原因是它权衡了子数组的规模和有序性;
 *
 *  比如数组为：[10, 8, 12, 9, 25, 28, 17, 50, 40, 100]，len=10, 则gap=4，即每隔4个元素为一组，
 *  则a[0], a[4], a[8]为一组，a[1], a[5], a[9]为一组，a[2], a[6]为一组，a[3], a[7]为一组，组内
 *  采用插入排序；然后gap减小为1，则此时每个元素自成一组，插入排序后排序结束。
 *  复杂度：时间O(n^2)，空间O(1)；
 *
 *  参考：http://algs4.cs.princeton.edu/21elementary/Shell.java.html
 *
 * User: lingguo
 * Date: 14-7-26 上午10:14
 */
public class ShellSort {

    public static void sort(int[] data, int len) {
        int gap, i, j;
        int tmp;
        for (gap = len >> 1; gap > 0; gap >>= 1) {
            for (i = gap; i < len; i++) {
                // 插入排序
                tmp = data[i];
                for (j = i - gap; j >= 0 && data[j] > tmp; j -= gap) {
                    data[j+gap] = data[j];
                }
                data[j + gap] = tmp;
            }
        }

    }
}
