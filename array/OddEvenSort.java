package io.nkcoder.alg.coding.array;

import java.util.Arrays;

/**
 * 问题描述：
 *  奇偶排序：输入一个整数数组，调整数组中元素的顺序，使所有奇数位于
 *  数组的前部分，所有偶数位于数组的后部分，要求时间复杂度为O(n)；
 *
 * 思路：
 *  【思路一】：使用两个指针p和q，分别指向数组的第一个元素和最后一个元
 *  素，相向遍历，相遇之前，当p指向是偶数，而q指向的是奇数时，则将两个
 *  数交换；
 *  【思路二】：借鉴快速排序的partition思路，一趟partition可以使所有小于
 *  哨兵的元素位于左部分，大于哨兵的元素位于右部分；这里将与哨兵的大小比
 *  较换成对元素的奇偶判断即可；使用两个指针p和q，p指向当前左部分奇数的
 *  最后一个元素，q用于遍历数组；初始情况下，p是空的，即指向数组首元素的
 *  前一个位置，q指向首元素，q单向遍历，当q指向的是偶数时，什么也不做，指
 *  针后移，如果q指向是奇数，则p向前移，然后交换p和q指向的元素，此时p仍指向
 *  左部分奇数元素的最后一个元素，继续判断q直到q遍历结束；
 *
 * @author: lingguo
 * @time: 2014/8/18 15:37
 */
public class OddEvenSort {

    /**
     * 第一种实现方式：两个指针，分别指向第一个元素和最后一个元素，相向遍历；
     *
     * @param data
     * @param lo
     * @param hi
     */
    public void sort1(final int[] data, final int lo, final int hi) {
        int p = lo;
        int q = hi;

        while (p < q) {
            // 找到第一个偶数
            while (data[p] % 2 != 0) {
                p++;
            }
            // 找到第一个奇数
            while (data[q] %2 == 0) {
                q--;
            }

            if (p >= q) {
                break;
            }
            // 交换
            swap(data, p, q);
        }
        Arrays.stream(data).forEach(System.out::println);
    }

    /**
     * 第二种实现方式：两个指针，第一个指针维护左端合法元素的界限，第二个指针用于
     * 遍历；
     *
     * @param data
     * @param lo
     * @param hi
     */
    public void sort2(final int[] data, final int lo, final int hi) {
        int p = lo - 1;
        int q = lo;

        // 遍历
        while (q < hi + 1) {
            if (data[q] % 2 == 0) {
                p++;
                swap(data, p, q);
            }
            q++;
        }

        Arrays.stream(data).forEach(System.out::println);
    }

    private void swap(int[] data, int i, int j) {
        int tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }
}
