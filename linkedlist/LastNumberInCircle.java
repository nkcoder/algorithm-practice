package io.nkcoder.alg.coding.linkedlist;


import io.nkcoder.alg.basic.datastructure.ListNode;

import java.util.ArrayList;

/**
 * 问题描述：
 *  有n个数，从0到n-1，形成环状，即n-1后的数字为0；从0开始，每次从环中删除第m个数，然后
 *  将删除元素的下一个元素作为第一个元素。如此循环，求最后剩下的数。
 *
 * 思路：
 *  思路一：构造环形链表，每个节点有两个属性，一个是值，另一个是指向下一个元素的指针。每次从
 *  环中删除第m个元素，直到链表中剩下一个元素。复杂度O(m*n)；
 *
 *  思路二：也是用链表模拟环，使用标准库的LinkedList，每次遍历到最后一个元素后，继续遍历链表
 *  第一个元素；时间和空间复杂度均为O(n)；
 *
 *  思路三：寻找规律：f(n,m)表示从n个数(0,1,2,...,n-1)的环中删除第m个数后剩下的数；第一
 *  次删掉了的元素为k（显然，k=m-1），剩下的元素为(k+1,k+2,...,n-1, 0, 1, ...,k-1)，此时
 *  的序列有n-1个元素，但排列不同，从该序列每次删除第m个元素后最后剩下的元素记为g(n-1,m)，则有
 *  f(n,m)=g(n-1,m)，但是我们将g(n-1,m)与f(n-1,m)（序列为0,1,2,...,n-2)做一下映射，找规律：
 *      g(n-1,m)       f(n-1,m)
 *      k+1             0
 *      k+2             1
 *      ...             ...
 *      k-2             n-3
 *      k-1             n-2
 *  发现的映射规律为：[f(n-1,m) + (k+1)] % n = g(n-1,m)
 *  将该公式以及(k=m-1)与f(n,m)=g(n-1,m)组合，有：
 *  f(n,m) = [f(n-1)+m]%n
 *  即如果要求f(n,m)，可以先求f(n-1)，得到的递归公式为：
 *      f(n,m) = [f(n-1)+m]%n, (其中，f(1,m)=0)
 *  利用该公式，使用递归或者循环很容易解决。复杂度O(n)
 *
 * User: Daniel
 * Date: 14-1-17
 * Time: 下午8:42
 */
public class LastNumberInCircle {

	/**
	 * 自定义链表节点，形成环状，每次从环中删除第m个节点，直到环中只剩下一个节点
     *
	 * @param head  头节点
	 * @param m 要删除的第m个节点
	 * @return  最后剩下的节点
	 */
	public static ListNode findByBuildingList(ListNode head, int m) {
		if (null == head || m <= 0) {
			return null;
		}

		// 寻找链表最后一个节点
		ListNode loopNode = head;
		while (loopNode.next != null) {
			loopNode = loopNode.next;
		}
        // 如果每次都是删除第一个节点，则返回的应该是最后一个节点
        if (m == 1) {
            return loopNode;
        }
        // 否则形成环状
		loopNode.next = head;

		// 循环删除，直到最后只剩下一个节点
		while (head.next != head) {
			// 先找到第m-1个节点
			ListNode preNode = head;
			for (int i = 0; i < m - 2; i++) {
				preNode = preNode.next;
			}
			// 删除第m个节点
			preNode.next = preNode.next.next;
			// 修改起点
			head = preNode.next;
		}
		return head;
	}

	/**
	 * 使用标准库的LinkedList，直接计算下一个要删除的元素的索引即可，直到链表中仅有一个元素；
     *
	 * @param numberList    包含n个数的链表
	 * @param m 要删除的第m个数
	 * @return  最后剩下的元素
	 */
	public static int findByArrayList(ArrayList<Integer> numberList, int m) {
		if (null == numberList || 0 == numberList.size()) {
            System.out.println("param error.");
			return -1;
		}

		int n = numberList.size();
		int start = 0;
		while (n > 1) {
			// 要删除的元素
			int delIndex = (start + m - 1) % n;
			numberList.remove(delIndex);
			n = numberList.size();
			// 删除元素后，该索引对应的即为下一个元素
			start = delIndex;
			if (start >= n) {
				start = 0;
			}
		}
		return numberList.get(0);
	}

	/**
	 * 使用循环实现由规律得出的公式：
	 *      f(n,m) = f'(n-1,m) = (f(n-1,m)+m) % n;
     * 因为数组的下标索引从0到n-1，所以返回的值即为最后元素在数组中的索引
     *
	 * @param m 要删除的第m个元素
     * @param n 数组的大小
	 * @return  数组中最后一个元素的索引
	 */
	public static int findByLoop(int n, int m) {

		// 只有一个元素：f(1,m) = 0;
		int last = 0;
		// 有n个元素：f(n,m) = (f(n-1,m) + m) % n;
		for (int i = 2; i <= n; i++) {
			last = (last + m) % i;
		}
		return last;
	}

	/**
	 * 使用递归实现由规律得到的公式：
	 *      f(n,m) = (f(n-1,m) + m) % n
     * 因为数组的下标索引从0到n-1，所以返回的值即为最后元素在数组中的索引
     *
	 * @param m 要删除的第m个元素
	 * @param n 数组中元素的个数
	 * @return  最后一个元素在数组里的索引
	 */
	public static int findByRecursion(int n, int m) {
		if (1 == n) {
			return 0;
		}
		return (findByRecursion(n - 1, m) + m) % n;
	}
}