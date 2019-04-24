"""
@link:

    [236. Lowest Common Ancestor of a Binary Tree](https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/)

@desc:

    Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.

    According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes v and w as the lowest node in T that has both v and w as descendants (where we allow a node to be a descendant of itself).”

            _______3______
           /              \
        ___5__          ___1__
       /      \        /      \
       6      _2       0       8
             /  \
             7   4
    For example, the lowest common ancestor (LCA) of nodes 5 and 1 is 3. Another example is LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.
"""
# Definition for a binary tree node.
class TreeNode(object):
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None

class Solution(object):

    def lowest_common_ancestor_by_subtree(self, root, p, q):
        """
        1. if p is in the left tree of root and q is in the right tree of root, root is LCA.
        2. if root is p or q, root is LCA.
        **Time Limit Exceeded**
        :type root: TreeNode
        :type p: TreeNode
        :type q: TreeNode
        :rtype: TreeNode
        """
        if not root or not p or not q:
            return None

        if root.val == p.val or root.val == q.val:
            return root

        if self.is_descendant(root.left, p) and self.is_descendant(root.left, q):
            return self.lowest_common_ancestor_by_subtree(root.left, p, q)
        elif self.is_descendant(root.right, p) and self.is_descendant(root.right, q):
            return self.lowest_common_ancestor_by_subtree(root.right, p, q)

        return root

    def is_descendant(self, x, y):
        """
        return true if y is descendant of x.
        :param x:   TreeNode
        :param y:   TreeNode
        :return:    boolean
        """
        if not x or not y:
            return False

        if x.val == y.val:
            return True

        return self.is_descendant(x.left, y) or self.is_descendant(x.right, y)

    def lowest_common_ancestor_by_recursion(self, root, p, q):
        """
        @Runtime: 124 ms
        @reference: https://leetcode.com/discuss/45386/4-lines-c-java-python-ruby

        :param root:
        :param p:
        :param q:
        :return:
        """
        if root in (None, p, q):
            return root

        left = self.lowest_common_ancestor_by_recursion(root.left, p, q)
        right = self.lowest_common_ancestor_by_recursion(root.right, p, q)
        if left and right:
            return root
        else:
            return left if left else right

if __name__ == "__main__":
    root = TreeNode(3)
    node5 = root.left = TreeNode(5)
    node1 = root.right = TreeNode(1)
    node6 = root.left.left = TreeNode(6)
    node2 = root.left.right = TreeNode(2)
    node0 = root.right.left = TreeNode(0)
    node8 = root.right.right = TreeNode(8)
    node7 = root.left.right.left = TreeNode(7)
    node4 = root.left.right.right = TreeNode(4)

    solution = Solution()

    #
    lca = solution.lowest_common_ancestor_by_subtree(root, node5, node1)
    assert lca.val == 3
    lca2 = solution.lowest_common_ancestor_by_subtree(root, node5, node4)
    assert lca2.val == 5
    lca3 = solution.lowest_common_ancestor_by_subtree(root, node7, node0)
    assert lca3.val == 3
    lca4 = solution.lowest_common_ancestor_by_subtree(root, node6, node2)
    assert lca4.val == 5

        #
    lca = solution.lowest_common_ancestor_by_recursion(root, node5, node1)
    assert lca.val == 3
    lca2 = solution.lowest_common_ancestor_by_recursion(root, node5, node4)
    assert lca2.val == 5
    lca3 = solution.lowest_common_ancestor_by_recursion(root, node7, node0)
    assert lca3.val == 3
    lca4 = solution.lowest_common_ancestor_by_recursion(root, node6, node2)
    assert lca4.val == 5