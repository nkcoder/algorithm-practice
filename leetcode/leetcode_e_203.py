"""
@link: [203. Remove Linked List Elements](https://leetcode.com/problems/remove-linked-list-elements/)

@desc:

    Remove all elements from a linked list of integers that have value val.

    Example
    Given: 1 --> 2 --> 6 --> 3 --> 4 --> 5 --> 6, val = 6
    Return: 1 --> 2 --> 3 --> 4 --> 5
"""


# Definition for singly-linked list.
from util.print_util import print_linkedlist


class ListNode(object):
    def __init__(self, x):
        self.val = x
        self.next = None


class Solution(object):
    def remove_elements(self, head, val):
        """
        Runtime: 116 ms

        :type head: ListNode
        :type val: int
        :rtype: ListNode
        """
        if not head:
            return None

        # find the new head of the list
        new_head = head
        while head and head.val == val:
            head = head.next
            new_head = head
        if not new_head:
            return None

        # remove elements with value in the list
        node = new_head
        while node:
            if node.next and node.next.val == val:
                node.next = node.next.next
            else:
                node = node.next
        return new_head


if __name__ == '__main__':
    solution = Solution()
    head1 = ListNode(1)
    head1.next = ListNode(2)
    head1.next.next = ListNode(2)
    head1.next.next.next = ListNode(1)

    new_head1 = solution.remove_elements(head1, 2)

    head2 = ListNode(4)
    new_head2 = solution.remove_elements(head2, 3)

    print_linkedlist(new_head1)

