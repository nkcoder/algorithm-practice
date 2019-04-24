package io.nkcoder.alg.basic.datastructure;

/**
 * 单链表
 *
 * User: daniel
 * Date: 6/11/16
 * Time: 09:47
 */
public class ListNode {
    public int value;
    public ListNode next;
    public ListNode(int value) {
        this.value = value;
        this.next = null;
    }

    /**
     * 反转单链表(前提是链表无环)
     * 如果只是让反转单链表，需要考虑到有环的情况
     *
     * @param head
     * @return
     */
    public ListNode reverse(ListNode head) {
        ListNode prev = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }

    /**
     * 另一种实现方式
     * 反转单链表(前提是链表无环
     * 如果只是让反转单链表，需要考虑到有环的情况
     * @param head
     * @return
     */
    public ListNode reverse2(ListNode head) {
        ListNode prev = null;
        while (head != null) {
            ListNode cur = head;
            head = head.next;
            cur.next = prev;
            prev = cur;
        }
        return prev;
    }

    /**
     * 递归实现
     * 反转单链表(前提是链表无环
     * 如果只是让反转单链表，需要考虑到有环的情况
     * 参考：http://stackoverflow.com/questions/354875/reversing-a-linked-list-in-java-recursively
     * @param head
     * @return
     */
    public ListNode reverseByRecursion(ListNode head) {
        // 没有节点
        if (head == null) {
            return null;
        }
        // 只有一个节点
        if (head.next == null) {
            return head;
        }
        ListNode secondNode = head.next;    // 下一个节点，递归部分的头节点
        head.next = null;       // 注意：必须断开当前节点的next连接
        ListNode reversedHead = reverseByRecursion(secondNode); // 反转后链表新的头节点
        secondNode.next = head;
        return reversedHead;
    }

    /**
     * 递归的另一种实现: 使用prev表示前一个节点，初始时为null
     * 反转单链表(前提是链表无环
     * 如果只是让反转单链表，需要考虑到有环的情况
     * 参考：http://stackoverflow.com/questions/354875/reversing-a-linked-list-in-java-recursively
     * @param head
     * @return
     */
    public ListNode reverseByRecursion2(ListNode head, ListNode prev) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            head.next = prev;
            return head;
        }
        ListNode reversedHead = reverseByRecursion2(head.next, head);
        head.next = prev;
        return reversedHead;
    }

    /**
     * 判断单链表是否有环
     * 时间复杂度：O(n): 因为fast比slow多走一圈
     *
     * @param head
     * @return
     */
    public boolean hasCircle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (slow != null && fast != null) {
            slow = slow.next;
            fast = fast.next;
            if (fast != null) {
                fast = fast.next;
            }
            if (fast == slow) {
                break;
            }
        }
        if (fast != null && slow != null && fast == slow) {
            return true;
        }
        return false;
    }

    /**
     * 反转单链表，确定链表有环的情形
     * @param head
     * @return
     */
    public ListNode reverseCircle(ListNode head) {
        ListNode headCopy = head;
        ListNode prev = null;
        // head作为当次节点来处理
        while (head.next != headCopy) {
            ListNode cur = head;
            head = head.next;
            cur.next = prev;
            prev = cur;
        }
        head.next = prev;       // 最后一个节点需要单独处理，因为循环退出，最后一个节点没处理
        headCopy.next = head;
        return head;
    }

    /**
     * 另一种实现
     * 反转单链表，确定链表有环的情形
     * @param head
     * @return
     */
    public ListNode reverseCircle2(ListNode head) {
        ListNode headCopy = head;
        // 从头节点的下一个节点开始处理
        ListNode prev = head;
        head = head.next;
        while (head != headCopy) {
            ListNode cur = head;
            head = head.next;
            cur.next = prev;
            prev = cur;
        }
        headCopy.next = prev;
        return prev;
    }
}
