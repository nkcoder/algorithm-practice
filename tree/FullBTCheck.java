package io.nkcoder.alg.coding.tree;

import io.nkcoder.alg.basic.datastructure.TreeNode;

import java.util.LinkedList;

/**
 * 满二叉树：如果二叉树的深度为h，除第h层外，其它各层的节点数都达到最大值。
 *  - 深度为h，总节点数应为2^(h+1)-1  (可以利用这个特性来判断)
 * 完全二叉树：对于深度为k，有n个节点的二叉树，当且仅当每一个节点与深度为k的满二叉树编号从1到n的节点一一对应。
 *  - 完全二叉树从根结点到倒数第二层满足完美二叉树，最后一层可以不完全填充，其叶子结点都靠左对齐。
 *  - 层次遍历变体，用队列实现，可以判断完全二叉树 
 * 
 * @author: lingguo
 * @time: 2014/10/15 21:11
 */
public class FullBTCheck {

    public boolean checkFullBT(TreeNode root) {
        if (root == null) {
            return true;
        } else if (root.left == null && root.right == null) {
            return true;
        } else if (root.left == null || root.right == null) {
            return false;
        }

        return checkFullBT(root.left) && checkFullBT(root.right);
    }

    public boolean checkCompleteBT(TreeNode root) {
        if (root == null) {
            return true;
        }

        LinkedList<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);
        TreeNode node = null;
        /**
         * 层次遍历，无论节点的左右节点是否为null，都放入队列
         */
        while ((node = nodeQueue.pop()) != null) {
            nodeQueue.push(node.left);
            nodeQueue.push(node.right);
        }

        /**
         * 如果是完全二叉树，则null都在队列的尾部
         */
        while (!nodeQueue.isEmpty()) {
            if (nodeQueue.pop() != null) {
                return false;
            }
        }
        return true;
    }

}
