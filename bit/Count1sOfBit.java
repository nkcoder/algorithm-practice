package io.nkcoder.alg.coding.bit;

/**
 * 问题描述：
 *  给定一个整数n，求n的二进制表示中1的个数；
 *
 * 思路：
 *  【思路一】：可以判断整数的二进制表示中每一位是否为1，即每次将整数与1进行与操作，然后整数右移一位，如果整数是正数，
 *  没问题，移位直到整数值为0，移位的次数为整数二进制的有效位数；如果整数是负数，直接循环移位，可能会陷入无
 *  限循环（负数最高位为1，右移时高位补符号位），此时需要完整移动32位（整数有4个字节构成），或者先求负数对应的正数
 *  的结果，然后加1；
 *  【思路二】：也是判断整数的每一位是否为1，但不是对整数移位，而是每次对1左移位，判断整数对应的位上是否为1；移位的次
 *  数为32位；
 *  【思路三】. n&(n-1)：每次消掉n最低位上的1，直到结果为0，比较的次数为整数二进制中1的个数。
 *
 * User: Daniel
 * Date: 13-12-17
 * Time: 下午9:13
 */
public class Count1sOfBit {

    /**
     * 通过n&(n-1)，每次可以消掉n的二进制表示中最低位上的1，比较
     * 的次数为n二进制中1的个数。
     *
     * @param num
     * @return
     */
    public static int countByAnd(int num) {
        int count = 0;
        while (0 != num) {
            count++;
            num = (num & (num - 1));
        }
        return count;
    }

    /**
     * 判断整数二进制的每一位上是否为1，每次对1进行左移位，直到变为0
     *
     * @param num
     * @return
     */
    public static int countByShiftLeft(int num) {
        int flag = 1;
        int count = 0;
        while (flag != 0) {     // 超出32位后就会变为0
            if ((flag & num) == flag) {
                count++;
            }
            flag = (flag << 1);
        }
        return count;
    }

    /**
     * 直接对整数移位，判断每一位是否为1.需要注意判断整数的正负，如果是整数，直接移位 ，
     * 如果是负数，不可直接移位，否则可能会陷入无限循环，此时可以移动完整的位数或者将负数
     * 转换为正数；
     *
     * @param num
     * @return
     */
    public static int countByShiftRight(int num) {
        int count = 0;
        if (num > 0) {
            while (num != 0) {
                if ((num & 0x01) == 1) {
                    count++;
                }
                num >>= 1;
            }
        } else {
            for (int i = 0; i < 32; i++) {
                if ((num & 0x01) == 1) {
                    count++;
                }
                num >>= 1;
            }
        }
        return count;
    }
}