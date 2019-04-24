package io.nkcoder.alg.coding.tree;

import io.nkcoder.alg.basic.datastructure.TreeNode;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * 问题描述：
 *  二叉树中，求和为某一值的路径（路径为根节点到叶节点的节点值的和），如果有多条路径符合要求，
 *  则都打印出来。
 *
 * 思路：
 *  遍历二叉树，使用一个缓冲区(栈而不是队列)保存当前的路径；对于一个节点，判断该节点是否为叶节点，
 *  如果是叶节点，则判断当前的路径和是否等于期望值，如果是，则打印路径，否则将当前节点从
 *  路径中移除；递归遍历。
 *
 * User: lingguo
 * Date: 14-3-27
 * Time: 上午12:03
 */
public class SumOfPath {

	/**
	 *
	 * @param root   树的根节点
	 * @param actualSum  遍历到当前节点时的节点和
	 * @param expectedSum    路径和的期望值
	 * @param path   用于保存路径节点
	 */
	public static void find(TreeNode root, int actualSum, int expectedSum, LinkedList<TreeNode> path) {
		//首先将当前节点添加到路径里，并更新节点和
		path.push(root);
		actualSum += root.value;
		//如果当前节点为叶子节点
		if (root.left == null && root.right == null) {
			if (actualSum == expectedSum) {
				printPath(path);
			}
			/* 当前节点是叶子节点，但当前路径和不符合要求，将当前节点从路径中移除;
             * 注意：这里不需要从节点和(actualSum)里减掉当前节点的值（即root.value)，是因为
             * actualSum是局部值，返回后，不影响原值。即path是引用，actualSum是值；
             */
			path.pop();
			return;
		}
		// 递归遍历左子树
		if (root.left != null) {
			find(root.left, actualSum, expectedSum, path);
		}
        // 递归遍历右子树
		if(root.right != null) {
			find(root.right, actualSum, expectedSum, path);
		}
        // 当前节点遍历完毕，返回上一个节点
		path.pop();
	}

    /**
     * 打印路径，因为path是栈，所以需要从栈底到栈顶打印
     *
     * @param path
     */
	private static void printPath(final LinkedList<TreeNode> path) {
        if (path != null) {
            Iterator<TreeNode> iterator = path.descendingIterator();
            while (iterator.hasNext()) {
                System.out.printf("->%d", iterator.next().value);
            }

        }
	}
}
