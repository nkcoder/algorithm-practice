"""
@link:

    [21. Merge Two Sorted Lists](https://leetcode.com/problems/merge-two-sorted-lists/)

@desc:

    Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists.

"""

# Definition for singly-linked list.
class ListNode(object):
    def __init__(self, x):
        self.val = x
        self.next = None


class Solution(object):
    def merge_two_lists_by_iteration(self, l1, l2):
        """
        traverse two lists and compare each node, very redundant.
        @Runtime: 56 ms

        :type l1: ListNode
        :type l2: ListNode
        :rtype: ListNode
        """
        if not l1:
            return l2
        if not l2:
            return l1

        head = None
        if l1.val <= l2.val:
            head = l1
            l1 = l1.next
        else:
            head = l2
            l2 = l2.next

        node = head
        while l1 and l2:
            if l1.val <= l2.val:
                node.next = l1
                node = l1
                l1 = l1.next
            else:
                node.next = l2
                node = l2
                l2 = l2.next

        if not l1:
            node.next = l2
        if not l2:
            node.next = l1

        return head

    def merge_two_lists_by_iteration_better(self, l1, l2):
        """
        traverse and compare, code is more succinct.
        @Runtime: 56 ms
        @Reference: https://leetcode.com/discuss/18986/14-line-clean-c-solution

        :param l1:
        :param l2:
        :return:
        """
        before_head = ListNode(0)
        node = before_head
        while l1 and l2:
            if l1.val <= l2.val:
                node.next = l1
                l1 = l1.next
            else:
                node.next = l2
                l2 = l2.next
            node = node.next

        node.next = l1 if l1 else l2

        return before_head.next

    def merge_two_lists_by_recursion(self, l1, l2):
        """
        by recursion, if the lists are too long, the stack may overflow.
        @Runtime: 68 ms
        @Reference: https://leetcode.com/discuss/8372/a-recursive-solution

        :param l1:
        :param l2:
        :return:
        """
        if not l1:
            return l2
        if not l2:
            return l1

        if l1.val <= l2.val:
            l1.next = self.merge_two_lists_by_iteration(l1.next, l2)
            return l1
        else:
            l2.next = self.merge_two_lists_by_recursion(l1, l2.next)
            return l2


if __name__ == '__main__':
    l1 = ListNode(1)
    l1.next = ListNode(3)
    l1.next.next = ListNode(5)
    l1.next.next.next = ListNode(7)

    l2 = ListNode(6)
    l2.next = ListNode(8)
    l2.next.next = ListNode(9)
    l2.next.next.next = ListNode(16)

    l3 = None

    solution = Solution()
    # head = solution.merge_two_lists_by_traverse(l1, l2)
    # head2 = solution.merge_two_lists_by_iteration_better(l1, l2)
    head = solution.merge_two_lists_by_recursion(l1, l2)

    while head:
        print('->{}'.format(head.val))
        head = head.next


