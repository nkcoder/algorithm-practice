package io.nkcoder.alg.coding.array;

/**
 * 问题描述：
 *  输入一个整型数组，数组中有整数，也有负数，数组中连续的一个整数或多个整数构成
 *  一个子数组，每一个整数都有一个和，求所有子数组的和的最大值；要求时间复杂度为
 *  O(n).
 *
 * 思路：
 *  假设已知以元素data[i]结尾的所有子数组的最大和为Ci，现在要求以元素data[i+1]结尾
 *  的所有子数组的最大和Cj (j = i + 1):
 *      - 如果Ci <= 0，则Cj = data[j];
 *      - 如果Ci > 0，则Cj = Ci + data[j];
 *  即，如果以前一个元素结尾的子数组的最大和小于等于0，则以当前元素结尾的子数组的最大
 *  和为当前元素，否则，将以前一个元素结尾的构成最大和的子数组与当前元素连接，新的子数组
 *  为当前元素结尾的最大子数组；
 *
 * @author: lingguo
 * @time: 2014/8/18 11:32
 * @update1: 2016/08/30
 */
public class MaxSubArray {

    /**
     * 求数组的连续子数组的最大和
     *
     * @param data
     * @param length
     * @return
     */
    public static int getMax(final int[] data, final int length) {
        int currSum = 0;
        int maxSum = Integer.MIN_VALUE;

        /**
         * 求以当前元素结尾的子数组的最大和，更新最大和
         */
        for (int i = 0; i < length; i++) {
            if (currSum <= 0) {
                currSum = data[i];
            } else {
                currSum += data[i];
            }

            if (currSum > maxSum) {
                maxSum = currSum;
            }
        }
        return maxSum;
    }

    /**
     * 通过三重循环求最大和，复杂度为O(n^3)，仅为开拓思路
     *
     * @param data
     * @param length
     * @return
     */
    public static int getMaxByLoop(final int[] data, final int length) {
        int currSum = 0;
        int maxSum = Integer.MIN_VALUE;

        /**
         * i和j定义了子数组的开闭，k用来遍历i和j构成的子数组
         */
        for (int i = 0; i < length; i++) {
            for (int j = i; j < length; j++) {
                for (int k = i; k <= j; k++) {
                    currSum += data[k];
                }
                if (currSum > maxSum) {
                    maxSum = currSum;
                }
                currSum = 0;
            }
        }
        return maxSum;
    }
}
