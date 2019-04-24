package io.nkcoder.alg.coding.array;

/**
 * 问题描述：
 *  给定一个二维数组：每一行的元素从左到右递增，每一列的元素从上到下递增；输入一个整数，求该整数是否
 *  在该二位数组中。比如，输入的二位数组为：
 *      2   5   7   9   10
 *      3   8   9   11  13
 *      5   9   12  15  20
 *      9   11  32  40  50
 *  如果输入的整数为12，则输出true，输入23，则输出false；
 *
 * 思路：
 *  因为数组的元素从左向右递增，从上到下递增，则以左下角的元素为例（右上角的元素亦可），如果被查找
 *  的整数比该元素大，由于该元素所在列的所有元素都比它小，因此该列可以去掉；如果被查找的整数比该元
 *  素小，则该元素所在的行可以去掉，这样每次比较都可以去掉一行或者一列。复杂度为O(m+n)（m、n分别为
 *  数组的行数和列数），该方法比直接遍历数组（复杂度O(mn)）要好。
 *
 * User: Daniel
 * Date: 13-12-5
 * Time: 下午11:16
 */
public class SearchInSortedArray {
	/**
	 * 在二维数组中查找元素
     *
	 * @param array  二维数组
	 * @param row    数组的行
	 * @param col 数组的列
	 * @param key    待查找的元素
	 * @return 存在则返回true，否则返回false
	 */
	public static boolean search(int[][] array, int row, int col, int key) {
		int i = row - 1, j = 0;
		while (i >= 0 && j <= col - 1) {
			if (array[i][j] == key) {
				return true;
			} else if (array[i][j] < key) {
				j++;
			} else {
				i--;
			}
		}
		return false;
	}
}
