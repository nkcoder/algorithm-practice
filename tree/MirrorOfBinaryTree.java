package io.nkcoder.alg.coding.tree;

import io.nkcoder.alg.basic.datastructure.TreeNode;
import java.util.LinkedList;

/**
 * 问题描述：
 *  求二叉树的镜像
 *
 * 思路：
 *  二叉树的镜像，就是将整个二叉树翻转过来，即将每个节点的左右节点都互换。所以只需要以某种方式遍历二叉树，交换
 *  每个节点的左右节点即可，比如前序遍历、中序遍历、后序遍历及层次遍历都行：
 *  思路一：通过递归，先序遍历，当节点不为空时，将左右子节点互换；
 *  思路二：通过队列，层次遍历。
 *
 * User: Daniel
 * Date: 13-12-22
 * Time: 上午11:29
 */

public class MirrorOfBinaryTree {

	/**
	 * 通过递归，先序遍历每一个节点，互换节点的左右子节点；
	 *
	 * @param root
	 */
	public static void mirrorByPreOrder(TreeNode root) {
		if (null == root) {
			return;
		}

		// 节点不为空，则交换其左右子节点
		TreeNode leftBak = root.left;
		root.left = root.right;
		root.right = leftBak;

		// 递归遍历左右子树
		mirrorByPreOrder(root.left);
		mirrorByPreOrder(root.right);
	}

	/**
	 * 借助队列，层次遍历，并交换左右子节点；
	 *
	 * @param root
	 */
	public static void mirrorByLayer(TreeNode root) {
		LinkedList<TreeNode> queue = new LinkedList<>();
		queue.offer(root);
		while (!queue.isEmpty()) {
			TreeNode currNode = queue.poll();
			if (currNode != null) {
                // 先将左右节点入队
				queue.offer(currNode.left);
				queue.offer(currNode.right);

                // 交换左右子节点
				TreeNode leftBak = currNode.left;
				currNode.left = currNode.right;
				currNode.right = leftBak;
			}
		}
	}
}
