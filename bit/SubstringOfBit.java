package io.nkcoder.alg.coding.bit;

/**
 * 问题描述：
 *  有两个32位的整数M和N，并给定两个整数i和j，设计一个方法，使得整数N中从i到j上的二进制表示与M的二进制表示
 *  相同，即M的二进制表示是N的二进制表示的子串。如N为10000000000， M为10101，i=2，j=6，则调用方法后，N变成
 *  了10001010100。
 *
 * 思路：
 *  将一个整数进行一次位操作后保持不变有两种方式：1)是同所有位均为1的数执行与操作；2)是同所有位都是0的数执行或操作；
 *  本例中，因为M之外的位均为0，所以采用`或`的方式，于是问题变为将N中第i位到第j位都置为0.
 *  1. 我们最终需要的是N的第i到第j位为0，其它位保持不变；
 *  2. 可以构造一个与操作，设置一个辅助的数P，P的第i位到第j位都为0，其它位都为1，则P&M后符合要求；
 *
 * User: lingguo
 * Date: 14-7-13
 */
public class SubstringOfBit {

    /**
     * 第一种思路：构造两个数，一个数的0到j位为1，其它位为0；第二个数的0到i为1，其余位为0；两个数相减后，i到j位为1，其余位为0，
     * 取反后即为所求。
     *
     * @param m
     * @param n
     * @param i
     * @param j
     * @return
     */
    public static int updateBits1(int m, int n, int i, int j) {
        int first = (1 << (j + 1)) - 1;     // 0到j位置1
        int second = (1 << (i)) - 1;    // 0到i位置1

        int mask = ~(first - second);       // i到j位置1，取反
        return (n & mask) | (m << i);       // 求结果
    }


    /**
     * 第二种思路：构造两个数，一个数的j+1位到32位置1，其余位为0；第二个数，将0到i位置1，其余位为0；
     * 两个数执行或操作后，i到j位为0，其余位为1，则mask即为所求；
     *
     * @param m
     * @param n
     * @param i
     * @param j
     * @return
     */
    public static int updateBits2(int m, int n, int i, int j) {
        int max = ~0;   // 所有位均为1，max值为-1，因为所有位均为1，是个复数，减1取反，得到其正数表示
        int left = max - ((1 << (j + 1)) - 1);      // 将j+1位到最高位(32)置1
        int right = (1 << (i)) - 1;             // 将0到i位置1

        int mask = left | right;            // 或操作得到mask
        return (n & mask) | (m << i);
    }
}
