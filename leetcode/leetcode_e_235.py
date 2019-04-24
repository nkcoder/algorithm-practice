"""
@link:

    [235. Lowest Common Ancestor of a Binary Search Tree](https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/)

@desc:

     Given a binary search tree (BST), find the lowest common ancestor (LCA) of two given nodes in the BST.

    According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes v and w as the lowest node in T that has both v and w as descendants (where we allow a node to be a descendant of itself).”

            _______6______
           /              \
        ___2__          ___8__
       /      \        /      \
       0      _4       7       9
             /  \
             3   5
    For example, the lowest common ancestor (LCA) of nodes 2 and 8 is 6. Another example is LCA of nodes 2 and 4 is 2, since a node can be a descendant of itself according to the LCA definition.
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
        @Runtime: 208 ms

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

    def lowest_common_ancestor_by_search(self, root, p, q):
        """
        use the property of binary search tree.
        1. if root.val == p.val or root.val == q.val, root is LCA.
        2. if root.val is between p.val and q.val, root is LCA.

        @Runtime: 132 ms

        :type root: TreeNode
        :type p: TreeNode
        :type q: TreeNode
        :rtype: TreeNode
        """
        if not root or not p or not q:
            return None

        if root.val < p.val and root.val < q.val:
            return self.lowest_common_ancestor_by_search(root.right, p, q)
        elif root.val > p.val and root.val > q.val:
            return self.lowest_common_ancestor_by_search(root.left, p, q)

        return root

    def lowest_common_ancestor_by_iteration1(self, root, p, q):
        """
        @Runtime: 132 ms
        @reference: https://leetcode.com/discuss/44959/3-lines-with-o-1-space-1-liners-alternatives

        :param root:
        :param p:
        :param q:
        :return:
        """
        if not root or not p or not q:
            return None

        while root:
            if p.val < root.val > q.val:
                root = root.left
            elif p.val > root.val < q.val:
                root = root.right
            else:
                return root

    def lowest_common_ancestor_by_iteration2(self, root, p, q):
        """
        @Runtime: 136 ms
        @reference: https://leetcode.com/discuss/44959/3-lines-with-o-1-space-1-liners-alternatives

        :param root:
        :param p:
        :param q:
        :return:
        """
        if not root or not p or not q:
            return None

        while (root.val - p.val) * (root.val - q.val) > 0:
            root = (root.left, root.right)[p.val > root.val]

        return root


if __name__ == "__main__":
    root = TreeNode(6)
    node2 = root.left = TreeNode(2)
    root.right = TreeNode(8)
    node0 = root.left.left = TreeNode(0)
    node4 = root.left.right = TreeNode(4)
    node7 = root.right.left = TreeNode(7)
    node9 = root.right.right = TreeNode(9)
    node3 = root.left.right.left = TreeNode(3)
    root.left.right.right = TreeNode(5)

    solution = Solution()

    #
    lca = solution.lowest_common_ancestor_by_subtree(root, node2, node4)
    assert lca.val == 2
    lca2 = solution.lowest_common_ancestor_by_subtree(root, node3, node7)
    assert lca2.val == 6
    lca3 = solution.lowest_common_ancestor_by_subtree(root, node3, node0)
    assert lca3.val == 2
    lca4 = solution.lowest_common_ancestor_by_subtree(root, node7, node9)
    assert lca4.val == 8

    #
    lca = solution.lowest_common_ancestor_by_search(root, node2, node4)
    assert lca.val == 2
    lca2 = solution.lowest_common_ancestor_by_search(root, node3, node7)
    assert lca2.val == 6
    lca3 = solution.lowest_common_ancestor_by_search(root, node3, node0)
    assert lca3.val == 2
    lca4 = solution.lowest_common_ancestor_by_search(root, node7, node9)
    assert lca4.val == 8

    #
    lca = solution.lowest_common_ancestor_by_iteration1(root, node2, node4)
    assert lca.val == 2
    lca2 = solution.lowest_common_ancestor_by_iteration1(root, node3, node7)
    assert lca2.val == 6
    lca3 = solution.lowest_common_ancestor_by_iteration1(root, node3, node0)
    assert lca3.val == 2
    lca4 = solution.lowest_common_ancestor_by_iteration1(root, node7, node9)
    assert lca4.val == 8

    #
    lca = solution.lowest_common_ancestor_by_iteration2(root, node2, node4)
    assert lca.val == 2
    lca2 = solution.lowest_common_ancestor_by_iteration2(root, node3, node7)
    assert lca2.val == 6
    lca3 = solution.lowest_common_ancestor_by_iteration2(root, node3, node0)
    assert lca3.val == 2
    lca4 = solution.lowest_common_ancestor_by_iteration2(root, node7, node9)
    assert lca4.val == 8