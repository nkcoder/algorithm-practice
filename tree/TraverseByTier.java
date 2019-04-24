package io.nkcoder.alg.coding.tree;


import io.nkcoder.alg.basic.datastructure.TreeNode;

import java.util.LinkedList;

/**
 * 问题描述：
 *  层次遍历二叉树；
 *
 * 思路：
 *  线序遍历+队列存储；
 *  先序遍历二叉树，将节点依次放入队列，先进先出，达到层次遍历的目的。
 *
 * User: Daniel
 * Date: 13-12-22
 * Time: 下午12:26
 */
public class TraverseByTier {

	/**
	 * 层次遍历二叉树
	 *
	 * @param head
	 */
	public static void traverse(TreeNode head) {
		if (null == head) {
			return;
		}

		// 使用队列保存要遍历的节点
		LinkedList<TreeNode> nodeQueue = new LinkedList<>();
		nodeQueue.offer(head);
		while (!nodeQueue.isEmpty()) {
			TreeNode node = nodeQueue.poll();
			printNode(node);
			if (node.left != null) {
				nodeQueue.offer(node.left);
			}
			if (node.right != null) {
				nodeQueue.offer(node.right);
			}
		}
	}

    /**
     * 打印节点的值
     *
     * @param node
     */
    private static void printNode(TreeNode node) {
        if (node != null) {
            System.out.printf("->%d", node.value);
        }
    }


}
