"""
@link: 

    [100. Same Tree](https://leetcode.com/problems/same-tree/)

@desc:

    Given two binary trees, write a function to check if they are equal or not.

    Two binary trees are considered equal if they are structurally identical and
    the nodes have the same value.
"""


# Definition for a binary tree node.
class TreeNode(object):
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None


class Solution(object):

    def is_same_tree1(self, p, q):
        """
        by recursion
        @time: 60ms

        :type p: TreeNode
        :type q: TreeNode
        :rtype: bool
        """
        if not p and not q:
            return True
        if (p and not q) or (not p and q):
            return False
        if (p and q) and (p.val != q.val):
            return False

        return self.is_same_tree2(p.left, q.left) and self.is_same_tree2(p.right, q.right)

    def is_same_tree2(self, p, q):
        """
        by recursion
        @time: 36ms (better solution!)

        :type p: TreeNode
        :type q: TreeNode
        :rtype: bool
        """
        if not p and not q:
            return True
        if not p or not q:
            return False

        if p.val == q.val:
            return self.is_same_tree(p.left, q.left) and self.is_same_tree(p.right, q.right)

        return False

    ###########################################################################

    def is_same_tree_by_traverse(self, p, q):
        """
        by results of traversal: pre-order and in-order can determine a tree structure.
        **WRONG ANSWER**: this method DOES NOT work when all node value are equal! For example:
            1       1
           /        \
          1          1

        :type p: TreeNode
        :type q: TreeNode
        :rtype: bool
        """
        preorder_of_p = self.preorder_traversal(p, [])
        inorder_of_p = self.inorder_traversal(p, [])
        preorder_of_q = self.preorder_traversal(q, [])
        inorder_of_q = self.inorder_traversal(q, [])

        if len(preorder_of_p) == len(preorder_of_q) and len(inorder_of_p) == len(inorder_of_q):
            for i in range(len(preorder_of_p)):
                if preorder_of_p[i].val != preorder_of_q[i].val:
                    return False

            for j in range(len(inorder_of_q)):
                if inorder_of_p[j].val != inorder_of_q[j].val:
                    return False
            return True
        return False

    def preorder_traversal(self, root, node_list):
        """
        pre-order traversal
        :param root:        root node
        :param node_list    contain all nodes of the traversal
        :return:
        """
        if not root:
            return node_list

        node_list.append(root)
        if root.left:
            self.preorder_traversal(root.left, node_list)
        if root.right:
            self.preorder_traversal(root.right, node_list)

        return node_list

    def inorder_traversal(self, root, node_list):
        """
        in-order traversal
        :param root:        root node
        :param node_list    contain all nodes of the traversal
        :return:
        """
        if not root:
            return node_list

        if root.left:
            self.inorder_traversal(root.left, node_list)

        node_list.append(root)

        if root.right:
            self.inorder_traversal(root.right, node_list)

        return node_list


if __name__ == '__main__':
    p = TreeNode(1)
    p.left = TreeNode(2)
    p.right = TreeNode(3)
    p.left.left = TreeNode(4)
    p.left.right = TreeNode(5)
    p.right.left = TreeNode(6)
    p.right.right = TreeNode(7)
    p.left.left.left = TreeNode(8)

    q = TreeNode(1)
    q.left = TreeNode(2)
    q.right = TreeNode(3)
    q.left.left = TreeNode(4)
    q.left.right = TreeNode(5)
    q.right.left = TreeNode(6)
    q.right.right = TreeNode(7)
    q.left.left.left = TreeNode(9)

    solution = Solution()
    # test preorder and inorder
    preorder_list = solution.preorder_traversal(p, [])
    for node in preorder_list:
        print('{0}->'.format(node.val), end='')
    print('')

    inorder_list = solution.inorder_traversal(p, [])
    for node in inorder_list:
        print('{0}->'.format(node.val), end='')
    print('')

    # test is same tree
    assert solution.is_same_tree(None, None) == True
    assert solution.is_same_tree(p, None) == False
    assert solution.is_same_tree(p, q) == False
