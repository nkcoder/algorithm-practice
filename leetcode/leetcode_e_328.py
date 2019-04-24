"""
@link:
    [328. Odd Even Linked List](https://leetcode.com/problems/odd-even-linked-list/)

@desc:

    Given a singly linked list, group all odd nodes together followed by the even nodes. Please note here we are talking about the node number and not the value in the nodes.

    You should try to do it in place. The program should run in O(1) space complexity and O(nodes) time complexity.

    Example:
    Given 1->2->3->4->5->NULL,
    return 1->3->5->2->4->NULL.

    Note:
    The relative order inside both the even and odd groups should remain as it was in the input.
    The first node is considered odd, the second node even and so on ...
"""

# Definition for singly-linked list.
class ListNode(object):
    def __init__(self, x):
        self.val = x
        self.next = None

class Solution(object):
    def odd_even_list(self, head):
        """
        traverse each node of the list and change pointer relation
        @Runtime: 72 ms

        :type head: ListNode
        :rtype: ListNode
        """
        if not head or not head.next:
            return head

        # save odd head and even head of original list
        odd_head = head
        even_head = head.next

        # traverse the list and change pointer relation
        node = odd_head
        while node.next:
            next_node = node.next
            node.next = node.next.next
            node = next_node

        # find the tail of odd list
        while odd_head.next:
            odd_head = odd_head.next

        odd_head.next = even_head

        return head

    def odd_even_list_better(self, head):
        """
        no need to find the tail of the odd list
        @Runtime: 64 ms

        :param head:
        :return:
        """
        if head:
            odd_node = head
            even_node = head.next
            even_head = even_node

            while even_node and even_node.next:
                odd_node.next = odd_node.next.next
                even_node.next = even_node.next.next
                odd_node = odd_node.next
                even_node = even_node.next

            odd_node.next = even_head
        return head

if __name__ == '__main__':

    solution = Solution()

    head1 = ListNode(10)
    head1.next = ListNode(20)
    head1.next.next = ListNode(30)
    head1.next.next.next = ListNode(40)
    head1.next.next.next.next = ListNode(50)
    head1.next.next.next.next.next = ListNode(60)
    head1.next.next.next.next.next.next = ListNode(70)
    head1.next.next.next.next.next.next.next = ListNode(80)
    new_head1 = solution.odd_even_list_better(head1)
    while new_head1:
        print('->{0}'.format(new_head1.val))
        new_head1 = new_head1.next

    head2 = ListNode(5)
    head2.next = ListNode(1)
    head2.next.next = ListNode(3)
    new_head2 = solution.odd_even_list_better(head2)
    while new_head2:
        print('->{0}'.format(new_head2.val))
        new_head2 = new_head2.next

