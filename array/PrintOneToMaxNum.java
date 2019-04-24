package io.nkcoder.alg.coding.array;


/**
 * 问题描述：
 *  输入整数n，打印从1到最大的n位数，比如n为4，输出1，2，3一直到最大的4位数，即9999；
 *
 * 思路：
 *  n值可能较大，所以此题为大数问题。大数问题一般可通过字符串或者数组来模拟解决。
 *  思路一：通过使用字符数组来模拟整数的加1操作。即每次在当前字符串表示的整数上加1，从最低位到最高位，记录进位。复杂度
 *  为O(10^n)；
 *  思路二：使用整数数组通过递归实现，即打印i位表示的整数，假设i-1表示的整数可列举，则只需将第i位从0到9遍历，同时与i-1位
 *  表示的整数序列组合即可。复杂度为O(10^n)；注意，递归可能会导致栈溢出。
 *
 * User: lingguo
 * Date: 14-3-3
 * Time: 下午10:47
 */
public class PrintOneToMaxNum {

	/**
	 * 【方法一】：用字符串（字符数组）模拟整数的加1操作：在字符数组表示的整数上加1，同时记录进位，进位从低位向高位传递，
     *      该操作在两种情况下退出：1. 在某一位上没有发生进位；2. 在最高位（字符数组第一位）发生了进位；
     *
	 * @param data  字符数组，表示当前整数
	 * @param length   字符串数组的长度
	 * @return  是否发生了溢出
	 */
	private static boolean addByOne(char[] data, int length) {
		boolean isOverflow = false;     // 溢出标志
		int carry = 0;                  // 进位标志
        // 从低位向高位遍历
		for (int i = length - 1; i >= 0; i--) {
			int sum = data[i] - '0' + carry;
			// 如果是最后一位，则加1
			if (i == length - 1) {
				sum++;
			}
			// 是否发生进位
			if (sum >= 10) {
				if (i == 0) {   // 如果最高位发生了进位，表示溢出
					isOverflow = true;
					break;
				}
				// 发生了进位，需要向更高一位加1
				data[i] = '0';
				carry = 1;
			} else {
				// 没有发生进位，加1操作结束
				data[i] = (char)(sum + '0');
				break;
			}
		}
		return isOverflow;
	}

    /**
     * 【方法一】：将字符数组表示的整数打印出来，去掉前缀0
     *
     * @param data  字符数组表示的整数值
     * @param length    字符数组的长度
     */
    private static void printNumber(char[] data, int length) {
        boolean zeroPrefix = true;
        for (int i = 0; i < length; i++) {
            if (zeroPrefix) {
                if (data[i] != '0') {
                    zeroPrefix = false;
                    System.out.print(data[i]);
                }
            } else {
                System.out.print(data[i]);
            }
        }
        System.out.println();
    }

	/**
	 * 【方法一】：循环模拟加1操作，直到发生溢出，则遍历了所有的整数
     *
	 * @param n  长度
	 */
	public static void stringMethod(int n) {
        char[] data = new char[n];
        for (int i = 0; i < n; i++) {
            data[i] = '0';
        }
		while (!addByOne(data, n)) {
			printNumber(data, n);
		}
	}

/************************************************************************************************************/

    /**
	 * 【方法二】：使用整数数组递归实现，数组索引较小表示整数的高位，比如要求第i位开始的所有整数，假设i-1位开始的所有
     *      整数都已经排列好，则只需将第i位从0到9遍历一遍即可。
     *
	 * @param data  整数数组
	 * @param length    数组的长度
	 * @param index     当前索引
	 */
	private static void recursiveAndPrint(int[] data, int length, int index) {
        // 如果遍历过了最后一位，则遍历结束，打印当前数组表示的整数
		if (index == length) {
			printNumber(data, length);
			return;
		}
        // 每一位都从0到9递归遍历
		for (int i = 0; i <= 9; i++) {
			data[index] = i;
			recursiveAndPrint(data, length, index + 1);
		}
	}

	/**
	 * 【方法二】：打印数组表示的整数值，去掉前缀0
     *
	 * @param data  数组表示的整数值
	 * @param length    数组的长度
	 */
	private static void printNumber(int[] data, int length) {
		boolean zeroPrefix = true;
		for (int i = 0; i < length; i++) {
			if (zeroPrefix == true) {
				if (data[i] != 0) {
					zeroPrefix = false;
					System.out.print(data[i]);
				}
			} else {
				System.out.print(data[i]);
			}
		}
		System.out.println();
	}

    /**
     * 【方法二】：递归打印所有的整数
     *
     * @param n
     */
    public static void recursiveMethod(int n) {
        int[] data = new int[n];
        for (int i = 0; i < n; i++) {
            data[i] = 0;
        }
        int index = 0;

        recursiveAndPrint(data, n, index);
    }

}
