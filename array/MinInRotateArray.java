package io.nkcoder.alg.coding.array;

/**
 * 问题描述：
 *  有一个递增排序的数组，将数组旋转（将前若干个元素移动数组的末尾）后，求旋转后数组的最小值；比如：
 *  有序数组为：2, 5, 8, 12, 21, 34, 50，旋转后为: 12,, 21, 34, 50, 2, 5, 8，求数组的最小值。
 *  输入：旋转后的的数组；
 *  输出：数组的最小值；
 *
 * 思路：
 *  【思路一】. 遍历整个数组寻找最小值，复杂度为O(n)；
 *  【思路二】. 旋转后，数组部分有序，可以利用二分查找的思路，降低复杂度：
 *      - 首先求中间元素，如果比首元素大，说明最小元素在后半区间；如果比尾元素小，说明最小元素在前半区间；调整二分查找的区间。
 *      - 二分的过程中，有一个特殊情况：即中间元素既大于等于首元素，又小于等于尾元素，亦即中间元素等于首元素，且等于尾元素，此时无法判断最小值位于哪个半区间，此时只能顺序查找。比如有两个旋转数组：[5, 5, 5, 5, 2, 2, 5]和[5, 2, 2, 5, 5, 5, 5]都可以看成数组[2, 2, 5, 5, 5, 5, 5]旋转得到的；这三种情形的条件及顺序是关键。
 *      - 还有一种特殊情况，即数组完全有序，亦即0个元素被旋转，此时不能直接二分，需要对这种情况做出处理；
 *
 *  所以需要注意三个问题：
 *    - 移动的元素可能为0；
 *    - 中间元素与首尾元素都相等；
 *    - 首尾元素索引相差为1时，尾元素为最小值；
 * 这里仅给出思路二的实现。
 *
 * User: lingguo
 * Date: 14-3-1
 * Update1: 16-08-30
 * Time: 下午8:53
 */
public class MinInRotateArray {

	/**
     * 在部分有序的数组里查找最小值(重点在查找算法，没有考虑无效输入等情况)
     *
	 * @param data  数组
	 * @param start 数组首元素下标
	 * @param end   数组尾元素下标
	 * @return  最小值
	 */
	public static int find(int[] data, int start, int end) {
        int mid = start;

        // 如果整个序列是有序的，直接返回
		while (data[start] >= data[end]) {
			// 仅有两个元素时,第二个元素为最小值
			if (start + 1 == end) {
				return data[end];
			}

			mid = (start + end) >> 1;
			// 顺序查找
			if (data[mid] == data[start] && data[mid] == data[end]) {
				return seqSearchMin(data, start, end);
			}
			// 最小元素位于右半区间
			if (data[mid] >= data[start]) {
				start = mid;
			} else if (data[mid] <= data[end]) {		// 最小元素位于左半区间
				end = mid;
			}
		}
		return data[mid];
	}

    /**
     * 遍历数组，寻找最小值
     *
     * @param data  数组
     * @param start 数组首元素索引
     * @param end   数组尾元素索引
     * @return  最小值
     */
	private static int seqSearchMin(int data[], int start, int end) {
		int min = data[start];
		for (int i = start;i <= end; i++) {
			if (data[i] < min) {
				min = data[i];
			}
		}
		return min;
	}

    public static void main(String[] args) {
        int[] data1 = {2, 5, 2, 2, 2};
        int result = find(data1, 0, data1.length - 1);
        System.out.println("result: " + result);
    }

}