package io.nkcoder.alg.coding.string;


/**
 * 问题描述：
 *  输入一个字符串，将其中的空格都替换为%20; 要求在原串上操作，不能使用辅助数组；
 *
 * 思路：
 *  C语言，字符串以'\0'结尾，所以可以先计算字符串中空格的数量，然后计算新串的大小，从后向前依次复制即可；
 *
 * User: Daniel
 * Date: 13-12-8
 * Time: 上午9:39
 */
public class StringReplace {

	public static char[] replaceBlanks(char[] original) {
		if (original == null || original.length == 0) {
			return original;
		}

		// 计算原串中空格的数量
		int numOfBlanks = 0;
		for (char c: original) {
			if (c == ' ') {
				numOfBlanks++;
			}
		}
		if (numOfBlanks == 0) {
			return original;
		}
		int newSize = original.length + numOfBlanks * 2;
		char[] newString = new char[newSize];

		// replace core
		int j = newSize - 1;
		for (int i = original.length - 1; i >= 0; i--) {
			if (original[i] == ' ') {
				newString[j--] = '0';
				newString[j--] = '2';
				newString[j--] = '%';
			} else {
				newString[j--] = original[i];
			}
		}
		return newString;
	}

}
