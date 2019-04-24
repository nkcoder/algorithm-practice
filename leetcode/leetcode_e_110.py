"""
@link: 

    [110. Balanced Binary Tree](https://leetcode.com/problems/balanced-binary-tree/)

@desc:

    Given a binary tree, determine if it is height-balanced.

    For this problem, a height-balanced binary tree is defined as a binary tree 
    in which the depth of the two subtrees of every node never differ by more 
    than 1.
"""

# Definition for a binary tree node.
class TreeNode(object):
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None

class Solution(object):

    # method one: 
    def get_depth_and_mark(self, root):
        """
        traverse recursively bottom up, use a global variable to record if the 
        tree is balance.
        """
        if not root or not self.is_balance:
            return 0

        left_depth = self.get_depth_and_mark(root.left)
        right_depth = self.get_depth_and_mark(root.right)

        if abs(left_depth - right_depth) > 1:
            self.is_balance = False

        return max(left_depth, right_depth) + 1

    def is_balanced_by_one_traverse(self, root):
        """
        traverse the tree once, if a subtree is not balanced, stop and return. 
        O(N)
        @Runtime: 76 ms
        @Reference: https://leetcode.com/discuss/73078/share-easy-understanding-python-solution-with-explanation
        """
        self.is_balance = True
        self.get_depth_and_mark(root)
        return self.is_balance

    # method 2
    def depth_of_tree(self, root):
        if not root:
            return 0
        left_depth = self.depth_of_tree(root.left)
        right_depth = self.depth_of_tree(root.right)
        return max(left_depth, right_depth) + 1

    def is_balaneced_by_double_traverse(self, root):
        """
        compare depth of subtrees of each node. O(N^2)
        @Runtime: 112 ms
        @Reference: https://leetcode.com/discuss/22898/the-bottom-up-o-n-solution-would-be-better

        :type root: TreeNode
        :rtype: bool
        """
        if not root:
            return True
        left_depth = self.depth_of_tree(root.left)
        right_depth = self.depth_of_tree(root.right)
        if abs(left_depth - right_depth) > 1:
            return False

        return self.is_balaneced_by_double_traverse(root.left) and self.is_balaneced_by_double_traverse(root.right)


if __name__ == "__main__":
    solution = Solution()

    root = TreeNode(1)
    root.left = TreeNode(2)
    root.right = TreeNode(3)
    root.left.left = TreeNode(4)
    root.left.right = TreeNode(5)
    root.left.left.left = TreeNode(6)
    root.left.left.right = TreeNode(7)

    print("I'm running.")

    assert solution.is_balaneced_by_double_traverse(root) == False
    assert solution.is_balanced_by_one_traverse(root) == False






