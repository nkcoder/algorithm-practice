"""
@link: 

    [104. Maximum Depth of Binary Tree](https://leetcode.com/problems/maximum-depth-of-binary-tree/)

@desc:

    Given a binary tree, find its maximum depth.

    The maximum depth is the number of nodes along the longest path from the 
    root node down to the farthest leaf node.

@reference: http://articles.leetcode.com/2010/04/maximum-height-of-binary-tree.html
"""


class TreeNode(object):
    def __init__(self, x):
        self.value = x
        self.left = None
        self.right = None


class Solution(object):
    def max_depth_by_recursion(self, root):
        """
        by recursion
        @Runtime: 60 ms

        :param root:
        :return:
        """
        if root is None:
            return 0
        left_max_depth = self.max_depth_by_recursion(root.left)
        right_max_depth = self.max_depth_by_recursion(root.right)
        return (left_max_depth + 1) if (left_max_depth > right_max_depth) else (right_max_depth + 1)


    def max_depth_by_iteration1(self, root):
        """
        by queue, record the depth of every level in the tree.
        similar to traversal by tier.
        @Runtime: 72 ms
        @reference: https://leetcode.com/discuss/63755/clean-java-iterative-solution

        :param root:
        :return:
        """
        if not root:
            return 0

        max_depth = 0
        queue = [root]
        while queue:
            queue_size = len(queue)
            for x in range(queue_size):
                node = queue.pop()
                if node.left:
                    queue.insert(0, node.left)
                if node.right:
                    queue.insert(0, node.right)

            max_depth += 1

        return max_depth

    def max_depth_by_iteration2(self, root):
        """
        by stack, record the current_depth of every current_node. easy to understand.
        @Runtime: 72 ms
        @reference: https://leetcode.com/discuss/46914/java-solution-both-recursion-and-iteration

        :param root:
        :return:
        """
        if not root:
            return 0

        node_stack = [root]
        depth_stack = [1]
        max_depth = 0

        while node_stack:
            current_node = node_stack.pop()
            current_depth = depth_stack.pop()

            # leaf node
            if not current_node.left and not current_node.right:
                max_depth = max(max_depth, current_depth)

            if current_node.left:
                node_stack.append(current_node.left)
                depth_stack.append(current_depth + 1)
            if current_node.right:
                node_stack.append(current_node.right)
                depth_stack.append(current_depth + 1)

        return max_depth

    def max_depth_by_iteration3(self, root):
        """
        by stack, post-order traverse. not very easy to understand
        @Runtime: 108 ms
        @reference: http://articles.leetcode.com/2010/04/maximum-height-of-binary-tree.html

        :param root:
        :return:
        """
        if root is None:
            return 0

        stack = [root]
        prev = None
        max_depth = 0
        while len(stack) > 0:
            cur = stack[len(stack) - 1]
            if not prev or prev.left == cur or prev.right == cur:
                if cur.left is not None:
                    stack.append(cur.left)
                elif cur.right is not None:
                    stack.append(cur.right)
            elif cur.left == prev:
                if cur.right is not None:
                    stack.append(cur.right)
            else:
                stack.pop()

            prev = cur
            if len(stack) > max_depth:
                max_depth = len(stack)

        return max_depth


if __name__ == "__main__":
    solution = Solution()
    root = TreeNode(1)
    root.left = TreeNode(2)
    root.right = TreeNode(3)
    root.left.left = TreeNode(4)
    root.left.left.right = TreeNode(5)

    assert solution.max_depth_by_recursion(root) == 4
    assert solution.max_depth_by_iteration1(root) == 4
    assert solution.max_depth_by_iteration2(root) == 4
    assert solution.max_depth_by_iteration3(root) == 4
