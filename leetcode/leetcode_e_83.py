"""
@link:

    [83. Remove Duplicates from Sorted List](https://leetcode.com/problems/remove-duplicates-from-sorted-list/)

@desc:

    Given a sorted linked list, delete all duplicates such that each element appear only once.

    For example,
    Given 1->1->2, return 1->2.
    Given 1->1->2->3->3, return 1->2->3.
"""


# Definition for singly-linked list.
class ListNode(object):
    def __init__(self, x):
        self.val = x
        self.next = None


class Solution(object):
    def delete_duplicates_by_list(self, head):
        """
        use a list to track all node values
        @Runtime: 64 ms

        :type head: ListNode
        :rtype: ListNode
        """
        if not head:
            return head

        val_list = [head.val]

        node = head
        while node.next:
            if node.next.val in val_list:
                node.next = node.next.next
            else:
                val_list.append(node.next.val)
                node = node.next

        return head

    def delete_duplicates_by_compare(self, head):
        """
        since it's sorted list, we can just compare adjacent nodes
        @Runtime: 68 ms

        :param head:
        :return:
        """
        if not head:
            return head

        node = head
        while node.next:
            if node.val == node.next.val:
                node.next = node.next.next
            else:
                node = node.next

        return head


if __name__ == "__main__":
    solution = Solution()

    head = ListNode(1)
    head.next = ListNode(2)
    head.next.next = ListNode(2)
    head.next.next.next = ListNode(3)
    head.next.next.next.next = ListNode(3)

    # head = solution.delete_duplicates_by_list(head)
    head = solution.delete_duplicates_by_compare(head)
    while head:
        print('->{}'.format(head.val))
        head = head.next
