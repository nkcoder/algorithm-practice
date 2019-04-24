package io.nkcoder.alg.coding.tree;


/**
 * 问题描述：
 *  判断一个序列是否为一棵二叉搜索树的后序遍历序列。
 *
 * 思路：
 *  1. 二叉搜索树的后序遍历序列的特征是：序列的最后一个元素为当前二叉树的根节点，该序列的左边
 *  一部分构成左子树，所有节点的值均小于根节点，序列的右边部分构成右子树，所有节点的值均大
 *  于根节点；
 *  2. 序列的最后一个元素为根节点，将序列分为两部分，如果左半部分的元素都小于该根节点，则可以构成左子树，
 *  如果右半部分的元素都大于根节点，则可以构成右子树；递归遍历即可。
 *  3. 复杂度：O(nlogn)
 *
 * User: lingguo
 * Date: 14-3-26
 * Time: 下午11:45
 */
public class IfPostOrderOfBST {

	/**
	 * 将序列根据最后一个节点一分为二，检查合法性，递归
	 *
	 * @param data  序列数组
	 * @param start 序列的第一个元素的索引
	 * @param end   序列的最后一个元素的索引
	 * @return  如果该序列可以为二叉树的后序遍历序列，返回true，否则返回false；
	 */
	public static boolean check(int[] data, int start, int end) {
		// 没有元素或仅有一个元素
		if (start >= end) {
			return true;
		}

        // 最后一个元素为根节点，分离左右子树
		int root = data[end];
		int left = start;
		while (left < end && data[left] < root) {
			left++;
		}
        // 判断右子树的元素是否都大于根元素
		int right = left;
		while (right < end && data[right] > root) {
			right++;
		}
		if (right != end) {
			return false;
		}
        // 递归遍历左右子树
		return check(data, start, left - 1) && check(data, left, end - 1);
	}
}
