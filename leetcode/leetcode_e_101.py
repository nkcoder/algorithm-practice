"""
@link: 

    [Symmetric Tree](https://leetcode.com/problems/symmetric-tree/)

@desc:

    Given a binary tree, check whether it is a mirror of itself (ie, symmetric 
    around its center).

    For example, this binary tree is symmetric:

        1
       / \
      2   2
     / \ / \
    3  4 4  3
    But the following is not:
        1
       / \
      2   2
       \   \
       3    3
    Note:
    Bonus points if you could solve it both recursively and iteratively.
"""

# Definition for a binary tree node.
class TreeNode(object):
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None


class Solution(object):
    
    # method 1
    def is_symmetric_by_stack(self, root):
        """
        push symmetric nodes to stack, compare and them push symmetric subnodes.
        @Runtime: 53 ms
        @Reference: https://leetcode.com/discuss/18354/recursive-and-non-recursive-solutions-in-java
        """
        if not root:
            return True

        node_stack = []
        if root.left:
            if not root.right:
                return False
            node_stack.append(root.left)
            node_stack.append(root.right)
        elif root.right:
            return False

        while node_stack:
            if len(node_stack) % 2 != 0:
                return False
            right_node = node_stack.pop()
            left_node = node_stack.pop()
            if left_node.val != right_node.val:
                return False

            if left_node.left:
                if not right_node.right:
                    return False
                node_stack.append(left_node.left)
                node_stack.append(right_node.right)
            elif right_node.right:
                return False

            if left_node.right:
                if not right_node.left:
                    return False
                node_stack.append(left_node.right)
                node_stack.append(right_node.left)
            elif right_node.left:
                return False

        return True

    # method 2
    def is_symmetric_by_recursion(self, root):
        """
        check by recursion.
        @Runtime: 52 ms
        @Reference: https://leetcode.com/discuss/18354/recursive-and-non-recursive-solutions-in-java

        :type root: TreeNode
        :rtype: bool
        """
        return not root or \
            self.is_symmetric_by_recursion_helper(root.left, root.right)

    def is_symmetric_by_recursion_helper(self, left, right):
        """
        compare symmetric nodes: left.left == right.right and 
            left.right == right.left
        """
        if not left or not right:
            return left == right
        if left.val != right.val:
            return False

        return self.is_symmetric_by_recursion_helper(left.left, right.right) \
            and self.is_symmetric_by_recursion_helper(left.right, right.left)


if __name__ == '__main__':
    root = TreeNode(1)
    root.left = TreeNode(2)
    root.right = TreeNode(2)
    root.left.left = TreeNode(3)
    root.left.right = TreeNode(4)
    root.right.left = TreeNode(4)
    root.right.right = TreeNode(3)

    root2 = TreeNode(1)
    root2.left = TreeNode(2)
    root2.right = TreeNode(3)
    root2.left.left = TreeNode(3)
    root2.right.left = TreeNode(2)

    solution = Solution()
    assert solution.is_symmetric_by_recursion(root) == True
    assert solution.is_symmetric_by_recursion(root2) == False

    assert solution.is_symmetric_by_stack(root) == True
    assert solution.is_symmetric_by_stack(root2) == False


