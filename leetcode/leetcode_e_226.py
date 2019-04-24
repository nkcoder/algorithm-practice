"""
@link: [226. Invert Binary Tree](https://leetcode.com/problems/invert-binary-tree/)

@desc:

    Invert a binary tree.

         4
       /   \
      2     7
     / \   / \
    1   3 6   9
    to
         4
       /   \
      7     2
     / \   / \
    9   6 3   1

"""


from util.print_util import print_tree
import collections

# Definition for a binary tree node.
class TreeNode(object):
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None


class Solution(object):
    def invert_tree_by_recursion(self, root):
        """
        invert tree by recursion
        @Runtime: 44 ms

        :type root: TreeNode
        :rtype: TreeNode
        """
        if root is None:
            return None

        root.left, root.right = root.right, root.left

        if root.left:
            self.invert_tree_by_recursion(root.left)
        if root.right:
            self.invert_tree_by_recursion(root.right)

        return root

    def invert_tree_by_queue(self, root):
        """
        invert tree by queue
        @Runtime: 48 ms

        :type root: TreeNode
        :rtype: TreeNode
        """
        if not root:
            return None

        queue = collections.deque([root])
        while len(queue) > 0:
            node = queue.popleft()
            if node.left:
                queue.append(node.left)
            if node.right:
                queue.append(node.right)
            if node.left or node.right:
                node.left, node.right = node.right, node.left

        return root

if __name__ == '__main__':
    solution = Solution()

    root = TreeNode(4)
    root.left = TreeNode(2)
    root.right = TreeNode(7)
    root.left.left = TreeNode(1)
    root.left.right = TreeNode(3)
    root.right.left = TreeNode(6)
    root.right.right = TreeNode(9)

    print_tree(root)
    print('')
    solution.invert_tree_by_queue(root)
    print_tree(root)
