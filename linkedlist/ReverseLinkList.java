package io.nkcoder.alg.coding.linkedlist;


import io.nkcoder.alg.basic.datastructure.ListNode;

/**
 * 问题描述：
 *  将单链表翻转，输出翻转后链表的头节点
 *
 * 思路：
 *  使用三个指针即可：当前指针，前一节点指针和后一节点指针。修改指针的指向，并向后移动指针，直到
 *  遍历结束。复杂度O(n)。
 *
 * User: lingguo
 * Date: 14-3-17
 * Time: 下午9:03
 */
public class ReverseLinkList {

	/**
	 * 翻转单链表
     *
	 * @param head  链表的头节点
	 * @return  翻转后的链表的头节点
	 */
	public static ListNode reverse(ListNode head) {
		// 初始状态
		ListNode previous = null;
        ListNode current = head;
		while (current != null) {
			ListNode next = current.next;
			current.next = previous;
			previous = current;
			current = next;
		}
		return previous;
	}
}
