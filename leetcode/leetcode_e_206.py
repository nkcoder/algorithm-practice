"""
@link:

    [206. Reverse Linked List](https://leetcode.com/problems/reverse-linked-list/)

@desc:

    Reverse a singly linked list.

"""
# Definition for singly-linked list.
class ListNode(object):
    def __init__(self, x):
        self.val = x
        self.next = None

class Solution(object):
    def reverse_list(self, head):
        """
        traverse each node and save the next node, then change pointer.
        @Runtime: 48 ms

        :type head: ListNode
        :rtype: ListNode
        """
        pre_node = None
        cur_node = head
        while cur_node:
            next_node = cur_node.next
            cur_node.next = pre_node
            pre_node = cur_node
            cur_node = next_node

        return pre_node

if __name__ == "__main__":
    head = ListNode(1)
    head.next = ListNode(2)
    head.next.next = ListNode(3)
    head.next.next.next = ListNode(4)
    head.next.next.next.next = ListNode(5)

    solution = Solution()
    new_head = solution.reverse_list(head)
    while new_head:
        print("->{0}".format(new_head.val))
        new_head = new_head.next


