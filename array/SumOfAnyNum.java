package io.nkcoder.alg.coding.array;

import java.util.LinkedList;
import java.util.ListIterator;

/**
 * 问题描述：
 *  输入整数n和sum，求序列1,2,3,...,n中所有和为sum的组合；
 *
 * 思路：
 *  - 1. 这是0/1背包类型的问题：有N件物品和一个容量为V的背包，放入第i件物品
 *  的容量消耗是Ci，产生的价值为Wi，求将哪些物品放入背包可使价值总和最大？
 *  0/1背包的关键在于每个物品仅有一件，可以选择放或者不放，用F[i,v]表示将前
 *  i件物品放入容量为V的背包得到的最大价值，则有状态转移方程：
 *      F[i,V] = max{F[i-1, V], F[i-1, V-Ci] + Wi}
 *  即如果不放第i件，则将前i-1件放入V中，如果放第i件，则表示将前i-1件放入V-Ci中，
 *  取二者最大值；(如果求N件物品，则i=N即可)
 *
 *  - 2. 针对本问题，以最终的序列中是否包含第n个数来分析，如果包含第n个数，则表示在
 *  剩下的n-1个数中求和为sum-n的序列，否则在剩下的n-1个数中求和为sum的序列；
 *
 * 参考：
 *  - https://github.com/nkcoder/The-Art-Of-Programming-By-July/blob/master/ebook/zh/02.03.md
 * User: lingguo
 * Date: 14-8-4 下午11:02
 */
public class SumOfAnyNum {

    public static void calculate(int n, int sum, LinkedList<Integer> stack) {
        /**
         * 退出条件
         */
        if (n <= 0 || sum <= 0) {
            return;
        }

        /**
         * 之前的序列加上当前的整数n，和符合要求，打印序列；
         * 注意：整数n是序列的一部分，但此时不在列表stack中，需要额外打印；
         */
        if (sum == n) {
            printList(stack);
            System.out.println(n);
        }

        /**
         * 先包含第n个数，求n-1个数的和为sum-n的序列
         */
        stack.push(n);
        calculate(n - 1, sum - n, stack);
        /**
         * 不包含第n个数，求n-1个数的和为sum的序列
         */
        stack.pop();
        calculate(n - 1, sum, stack);
    }

    /**
     * 逆序打印链表
     *
     * @param stack
     */
    private static void printList(LinkedList<Integer> stack) {
        ListIterator iterator = stack.listIterator(stack.size());
        while (iterator.hasPrevious()) {
            System.out.print(iterator.previous() + "->");
        }
    }

    /**
     * 注意：LinkedList是一个双向的链表，push和pop操作都是在链表头部操作，分别是增加
     * 新的头节点和删除当前头节点；
     */

}


