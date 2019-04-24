package io.nkcoder.alg.basic.datastructure;

/**
 * 二叉树基本属性:
 *  - 第i层最多有2^(i-1)个节点;
 *  - 深度为k, 最多有(2^k - 1)个节点;
 *  - 对任意二叉树, 度为0的节点数与度为2的节点数的关系: n0 = n2 + 1;
 *  - 满二叉树: 深度为k, 且有(2^k - 1)个节点;
 *  - 完全二叉树: 深度为k, 有n个节点, 当且仅当每一个节点与深度为k的满二叉树中序号为1至n的节点对应时;
 *
 * 二叉树的遍历:
 *  - 深度优先: 使用递归, 即栈实现
 *      - 前序: 先根后左再右
 *      - 中序: 先左后根再右
 *      - 后序: 先左后右再根
 *  - 广度优先: 使用队列实现
 *      - 先访问根节点, 沿着树的宽度遍历子节点, 直到所有节点均被访问为止;
 *
 * 二叉树的构造:
 *  - 已知中序遍历, 以及前序遍历或后序遍历, 可以恢复原二叉树结构, 前提是节点元素不重复!
 *
 * 二叉查找树:
 *  - 每个节点的键值都大于等于左子树中任意节点的键值, 而小于等于右子树中任意节点的键值;
 *  - 中序遍历可得到有序数组;
 *
 * created by daniel at 6/11/16 12:40
 */
public class TreeNode {
    public int value;
    public TreeNode left, right;
    public TreeNode(int value) {
        this.value = value;
        this.left = this.right = null;
    }


    public void preorder(TreeNode root) {
        if (root != null) {
            System.out.println(root.value);
            preorder(root.left);
            preorder(root.right);
        }
    }

    public void inorder(TreeNode root) {
        if (root != null) {
            inorder(root.left);
            System.out.println(root.value);
            inorder(root.right);
        }
    }

    public void postorder(TreeNode root) {
        if (root != null) {
            postorder(root.left);
            postorder(root.right);
            System.out.println(root.value);
        }
    }

}
