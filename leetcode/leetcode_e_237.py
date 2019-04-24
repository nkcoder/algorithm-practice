"""
@link: [237. Delete Node in a Linked List](https://leetcode.com/problems/delete-node-in-a-linked-list/)

@desc:

    Write a function to delete a node (except the tail) in a singly linked list, given only access to that node.

    Supposed the linked list is 1 -> 2 -> 3 -> 4 and you are given the third node with
    value 3, the linked list should become 1 -> 2 -> 4 after calling your function.
"""

# Definition for singly-linked list.
from util.print_util import print_linkedlist


class ListNode(object):
    def __init__(self, x):
        self.val = x
        self.next = None


class Solution(object):
    def delete_node(self, node):
        """
        update the current node value with the value of the next and delete the next node.
        @Runtime: 56 ms

        :type node: ListNode
        :rtype: void Do not return anything, modify node in-place instead.
        """
        if node is None or node.next is None:
            return
        node.val = node.next.val
        node.next = node.next.next

if __name__ == '__main__':
    solution = Solution()
    head = ListNode(1)
    head.next = ListNode(2)
    node3 = head.next.next = ListNode(3)
    node3.next = ListNode(4)

    solution.delete_node(node3)

    print_linkedlist(head)
