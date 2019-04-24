package io.nkcoder.alg.coding.tree;


import io.nkcoder.alg.basic.datastructure.TreeNode;

import java.util.LinkedList;

/**
 * 问题描述：
 *    给定二叉树中的两个节点，求这两个节点的第一个公共父节点。
 *
 * 思路：
 *  【讨论一】：如果这个一棵二叉搜索树，由于节点值之间的大小关系，遍历二叉树，找到这样一个节点，其节点值在两
 *      个已知节点值之间；
 *  【讨论二】：如果二叉树中的每个节点都有指向其父节点的指针，则问题转换为求两个单链表的第一个公共节点:
 *      先分别遍历两个链表的长度，得到长度差d，然后将长链表先遍历d次，然后两个链表同步遍历，找到第一个相同的节点。
 *  【讨论三】：如果这只是一棵普通的二叉树，则有两种思路：
 *      思路一：分别求根节点到两个已知节点的路径，则第一个公共父节点即为两条路径的最后一个相交节点。
 *      思路二：对于公共父节点，则两个给定节点必分别位于其左右子树中。此时又有两种思路，一种是直接遍历二叉树，
 *      判断两个节点是否分别位于当前节点的左右子树中，该思路中有的节点会被重复遍历多次；另一种思路是，先判断
 *      给定节点在当前节点左右子树中的个数，比如如果两个节点都位于左子树，则遍历左子树，如果都位于右子树，则遍历
 *      右子树，如果左右子树各一个，则当前节点即为所求；
 *      注意：特殊情况，两个节点相同或者互为父子节点；
 *
 * User: Daniel
 * Date: 14-1-12
 * Time: 下午8:28
 */
public class FirstCommonParent {

    /**
     * 【方法一】：已知根节点到两个节点的路径后，它们的第一个公共父节点即两条路径的最后一个
     *  交点
     *
     * @param root
     * @param first
     * @param second
     * @return
     */
    public static TreeNode findParentByPath(TreeNode root, TreeNode first, TreeNode second) {
        if (null == root || null == first || null == second) {
            return null;
        }

        LinkedList<TreeNode> firstPath = new LinkedList<TreeNode>();
        LinkedList<TreeNode> secondPath = new LinkedList<TreeNode>();

        //  获取路径
        boolean leftFound = getTreePath(root, first, firstPath);
        boolean rightFound = getTreePath(root, second, secondPath);

        //  计算两条路径的最后一个交点
        if (leftFound && rightFound) {
            int start = 0;
            while (start < firstPath.size() && start < secondPath.size()) {
                if (firstPath.get(start) != secondPath.get(start)) {
                    return firstPath.get(start - 1);
                }
                start++;
            }

            //特殊情况处理：两个节点互为父子节点，或者两个节点相同
            if (start == firstPath.size() || start == secondPath.size()) {
                if (firstPath.size() < secondPath.size()) {
                    return firstPath.get(start - 1);
                } else {
                    return secondPath.get(start - 1);
                }
            }
        }
        return null;
    }

    /**
     * 【方法一】：遍历二叉树，计算根节点到某一节点的路径，关键是在遍历完当前节点后
     * 一定要记得从路径中删除当前节点，否则该节点一直存在于路径中。
     *
     * @param root
     * @param node
     * @param path
     * @return
     */
    public static boolean getTreePath(TreeNode root, TreeNode node, LinkedList<TreeNode> path) {
        if (null == root) {
            return false;
        }

        //  将当前节点加入到路径中
        path.add(root);

        //   当前节点即是，则返回
        if (node == root) {
            return true;
        }

        //  当前节点不是，递归查找左子树和右子树
        boolean isFound = false;
        isFound = getTreePath(root.left, node, path);
        if (!isFound) {
            isFound = getTreePath(root.right, node, path);
        }

        //  当前节点及其左右子树中都没有该节点，从路径中去掉该节点(关键)，继续遍历
        if (!isFound) {
            path.remove(root);
        }

        return isFound;
    }
    /**********************************************************************************************/


    private static int NO_NODES_FOUND = 0;
    private static int ONE_NODE_FOUND = 1;
    private static int TWO_NODES_FOUND = 2;

    /**
     * 如果两个节点都在左子树：如果左子树是其中某一个节点，则左子树根节点即为所求；否则递归遍历左子树；
     * 如果两个节点都在右子树，同理；
     * 如果两个节点分别在左右子树，则当前节点即为所求。
     *
     * @param root
     * @param first
     * @param second
     * @return
     */
    public static TreeNode findParentByNodeNum(TreeNode root, TreeNode first, TreeNode second) {
        //
        if ((first == second) && (root.left == first || root.right == second)) {
            return root;
        }

        // 遍历左子树
        int nodesFromLeft = covers(root.left, first, second);
        // 两个节点都在左子树，判断左子树根节点是否是其中某一个节点
        if (nodesFromLeft == TWO_NODES_FOUND) {
            if (root.left == first || root.left == second) {
                return root.left;
            } else {
                findParentByNodeNum(root.left, first, second);
            }
        } else if (nodesFromLeft == ONE_NODE_FOUND) {       // 只有一个节点在左子树，需要判断当前节点
            if (root == first) {
                return first;
            } else if (root == second) {
                return second;
            }
        }

        // 遍历右子树
        int nodesFromRight = covers(root.right, first, second);
        if (nodesFromRight == TWO_NODES_FOUND) {
            if (root.right == first || root.right == second) {
                return root.right;
            } else {
                findParentByNodeNum(root.right, first, second);
            }
        } else if (nodesFromRight == ONE_NODE_FOUND) {
            if (root == first) {
                return first;
            } else if (root == second) {
                return second;
            }
        }

        // 一个节点在左子树，一个节点在右子树
        if (nodesFromLeft == ONE_NODE_FOUND && nodesFromRight == ONE_NODE_FOUND) {
            return root;
        }
        return null;
    }

    /**
     * 在以root为根的二叉树中，p和q出现的次数
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    private static int covers(TreeNode root, TreeNode p, TreeNode q) {
        int num = NO_NODES_FOUND;
        // 当前节点为空，肯定不包含p或q
        if (root == null) {
            return num;
        }
        // 当前节点为p或q，统计加1
        if (root == p || root == q) {
            num++;
        }

        // 统计左右子树中p和q的个数，如果已经找到2个，则提前退出
        num += covers(root.left, p, q);
        if (num == TWO_NODES_FOUND) {
            return num;
        }
        return num+covers(root.right, p, q);
    }

}
