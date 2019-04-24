package io.nkcoder.alg.coding.bit;

/**
 * 问题：不使用加法（+）、减法（-）、乘法（*）和除法（/)操作，求两个数的和。
 *
 * 思路：
 *  可以从位运算上考虑，将两个整数以二进制表示，两个数的加法，即对应位上的加法，如果两个数的某一位不同（一个为0，一个为1），
 *  则相加后该位为1；如果相同，分两种情况，同为0，则相加后还是0，如果同为1，相加后结果仍为0，但需要向更高位进位；
 *  于是我们发现，对位进行相加时，如果两个位相同，结果为0，不同，结果为1，这即位的异或运算；进位仅发生在两个位同为1的情况下，
 *  即位的与运算，与运算的结果，位为1的表示该位上发生了进位，进位实质上就是在更高位上加1，即左移一位，于是，最终的结果为异或
 *  的结果与进位的结果之和，问题又回到了求两个数之和，重复上述操作，直到进位为0为止。
 *
 * User: Daniel
 * Date: 14-1-14
 * Time: 下午9:30
 */
public class SumByBit {
	/**
	 * 输入两个整数，求它们的和（不能使用加减乘除四种运算）
     *
	 * @param firstNum
	 * @param secondNum
	 * @return
	 */
	public static int cal(int firstNum, int secondNum) {
		int bitXor = 0;
		int bitAnd = 0;
		while (0 != secondNum) {
			bitXor = firstNum ^ secondNum;  // 异或，提取出不同位
			bitAnd = firstNum & secondNum;  // 与，提取进位
			firstNum = bitXor;
			secondNum = bitAnd << 1;        // 进位需要左移
		}
		return firstNum;
	}
}