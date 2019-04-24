package io.nkcoder.alg.coding.array;

/**
 * 问题描述：
 *  一个台阶一共有n级，如果一次可以跳1级，也可以跳2级，请问一共有多少
 *  跳法，分析时间复杂度；
 *
 * 思路：
 *  这是Fibonacci问题：假设n级台阶一共的跳法为f(n)，如果第一次跳1级，则
 *  剩下的n-1级台阶的跳法为f(n-1)；如果第一次跳2级，则剩下的n-2级台阶的
 *  跳法为f(n-2)，则总共n级台阶的跳法为：f(n) = f(n-1) + f(n-2)；当n=1
 *  时，f(1) = 1， 当n=2时，f(n) = 2；
 *
 * @author: lingguo
 * @time: 2014/8/18 16:19
 */
public class TakeStairs {

    /**
     * n级台阶的总跳法
     *
     * @param n
     * @return
     */
    public static int jump(int n) {
        int f1 = 1;
        int f2 = 2;
        for (int i = 3; i <= n; i++) {
            int f = f1 + f2;
            f1 = f2;
            f2 = f;
        }
        return f2;
    }

}
