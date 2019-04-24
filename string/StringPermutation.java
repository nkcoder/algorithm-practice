package io.nkcoder.alg.coding.string;

/**
 * 问题描述：
 *  给定一个包含n个字符的字符串，打印所有的排列；如输入abc，则打印abc, acb, bac, bca, cab, cba;
 *
 * 思路：
 *  【思路一】：将全排列分为两部分，第1个字符和剩下的(n-1)个字符；将(n-1)个字符全排列，然后将第一个字符
 *  逐一与剩下的每个字符互换位置，每次将新的(n-1)个字符全排列；当第1个字符与(n-1)个字符都互换且全排列完毕
 *  后，全排列完毕；复杂度O(n^2)；
 *
 *  【思路二】：也是递归的策略，画图比较好理解，参考：
 *      http://introcs.cs.princeton.edu/java/23recursion/Permutations.java.html
 *
 *  【思路三】：无论是整数还是数字，都可以比较大小，因此判断当前排列的下一个排列，
 *      参考：{@link }
 * User: Daniel
 * Date: 13-12-29
 * Time: 上午8:10
 */
public class StringPermutation {

	/**
	 * 递归实现
	 *
	 * @param string
	 * @param index
	 */
	public static void permBySwap(char[] string, int index) {
		// 构成全排列
		if (index == string.length) {
			printPerm(string);
		} else {
			for (int i = index; i < string.length; i++) {
				// 将第一个字符与字符串的每一个字符(包括本身)互换
				char charBak = string[index];
				string[index] = string[i];
				string[i] = charBak;

				// 递归求除当前字符外的字符串构成的全排列
				permBySwap(string, index + 1);

				// 互换，重置为初始状态
				char charBak2 = string[index];
				string[index] = string[i];
				string[i] = charBak2;
			}
		}
	}

	/**
	 * 前缀法，画图帮助理解
	 *
	 * @param prefix
	 * @param string
	 */
	public static void permByPrefix(String prefix, String string) {
		int length = string.length();
		if (0 == length) {
			printPerm(prefix.toCharArray());
		} else {
			for (int i = 0; i < length; i++) {
				permByPrefix(prefix + string.charAt(i), string.substring(0, i) + string.substring(i + 1, length));
			}
		}
	}

    /**
     * 输出一个排列串
     *
     * @param perm
     */
    private static void printPerm(char[] perm) {
        System.out.printf("perm: %d", perm);
    }

}
