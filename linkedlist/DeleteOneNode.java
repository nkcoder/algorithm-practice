package io.nkcoder.alg.coding.linkedlist;


import io.nkcoder.alg.basic.datastructure.ListNode;

/**
 * 问题：
 *  给定一个链表和其中一个节点，删除该节点；
 *
 * 思路：
 *  节点的差异体现在节点对象的内容不同。要删除当前节点，可以将当前节点与下一节点的值互换，然后删除下一个节点即可；
 *  复杂度为O(1)；
 *  需要注意两种特殊情况：
 *  - 如果要删除的节点是最后一个节点，没有下一个节点，此时需要从头遍历了；复杂度为O(n)；
 *  - 如果链表只有一个节点，则删除后链表为空；
 *
 * User: lingguo
 * Date: 14-3-10
 * Time: 下午11:49
 */
public class DeleteOneNode {

    /**
     * 从链表中删除某一个节点
     *
     * @param head  链表的头节点
     * @param toDelete  要删除的节点
     * @return 头节点
     */
	public static ListNode delete(ListNode head, ListNode toDelete) {
		// param error
		if (head == null || toDelete == null) {
            System.out.println("param error.");
			return null;
		}
		// 最后一个节点
		if (toDelete.next == null) {
			// 也是头节点
			if (head == toDelete) {
                System.out.println("deleted the only one node.");
                return null;
			}
			// 遍历查找前一个节点
			ListNode node = head;
			while (node.next != toDelete) {
				node = node.next;
			}
			node.next = node.next.next;
			return head;
		}

        // 不是最后一个节点，将下一个节点的值覆盖当前节点的值，删除下一个节点
		toDelete.value = toDelete.next.value;
		toDelete.next = toDelete.next.next;
		return head;
	}

}