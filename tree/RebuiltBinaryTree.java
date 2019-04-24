package io.nkcoder.alg.coding.tree;


import io.nkcoder.alg.basic.datastructure.TreeNode;

/**
 * 问题描述：
 *  重建二叉树：根据二叉树的前序和中序遍历序列，重建二叉树，并输出二叉树的后序遍历序列。
 *
 * 思路：
 *  前序遍历的第一个元素是当前二叉树的根节点，使用根节点和中序遍历序列，可以将当前二叉树
 *  的左右子树分离，同时根节点与左右子树之间建立父子关系，递归即可重建该二叉树；
 *
 * User: Daniel
 * Date: 13-12-9
 * Time: 下午10:51
 */
public class RebuiltBinaryTree {

	//全局标志
	private static boolean status = true;

	/**
	 * 根据前序和中序遍历序列，重建二叉树
     *
	 * @param preOrder   前序遍历序列
	 * @param preStart   前序遍历序列起始元素的下标
	 * @param preEnd     前序遍历序列的最后一个元素的下标
	 * @param inOrder    后序遍历序列
	 * @param inStart    后序遍历序列起始元素的下标
	 * @param inEnd      后序遍历序列的最后一个元素的下标
	 * @return
	 */
	public static TreeNode rebuilt(int[] preOrder, int preStart, int preEnd,
                                   int[] inOrder, int inStart, int inEnd) {
		// 前序遍历的一个元素为当前二叉树的根节点
		TreeNode root = new TreeNode(preOrder[preStart]);

        // 返回条件：只有一个元素
		if ((preStart == preEnd) && (inStart == inEnd)) {
			if (preOrder[preStart] == inOrder[inStart]) {
				return root;
			} else {
                System.out.println("error input");
				status = false;
				return null;
			}
		}

        // 在中序遍历序列中查找根节点
		int index = indexOf(inOrder, preOrder[preStart]);
		if (index == -1) {
            System.out.println("error input");
			status = false;
			return null;
		}
        // 重建左子树
        int leftLength = index - inStart;
		if (index > inStart) {
			root.left = rebuilt(preOrder, preStart+1, preStart+leftLength,
					inOrder, inStart, inStart+leftLength-1);
		}
        // 重建右子树
		if (index < inEnd) {
			root.right = rebuilt(preOrder, preStart+leftLength+1, preEnd,
					inOrder, inStart+leftLength+1, inEnd);
		}

		return root;
	}

	/**
	 * 在数组中查找某一个元素
     *
	 * @param data  the array to search
	 * @param key   the key to search for
	 * @return
	 */
	private static int indexOf(int[] data, int key) {
		for (int i = 0; i < data.length; i++) {
			if (data[i] == key) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * 后序遍历二叉树
     *
	 * @param root
	 */
	public static void postOrderTraverse(TreeNode root) {
		if (null == root) {
			return;
		}
		postOrderTraverse(root.left);
		postOrderTraverse(root.right);
		printNode(root);
	}

    /**
     * 打印节点的值
     *
     * @param node
     */
    private static void printNode(TreeNode node) {
        if (node != null) {
            System.out.printf("%d", node.value);
        }
    }

}