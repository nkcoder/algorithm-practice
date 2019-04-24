package io.nkcoder.alg.coding.stackqueue;

import java.util.LinkedList;

/**
 * 问题描述：
 *  使用两个栈实现队列;
 *
 * 思路：
 *  栈1用于入队，栈2用于出队，当栈2为空时，将栈1的所有元素全部
 *  出栈并压入栈2即可；注意，必须是在栈2为空时才能将栈1中的所有元素出栈压入栈2.
 *
 * User: Daniel
 * Date: 13-12-14
 * Time: 上午8:13
 */
public class QueueByStacks {
	private static LinkedList<Integer> inStack = new LinkedList<Integer>();
	private static LinkedList<Integer> outStack = new LinkedList<Integer>();

	/**
	 *  模拟出队操作：从outStack出栈，如果outStack为空，则将inStack的所有元素
     *   出栈并压入到outStack中，如果outStack还是空，则队列为空；
     *
	 * @return
	 */
	public static Integer deQueue() {
		if (outStack.isEmpty()) {
			while (!inStack.isEmpty()) {
				int element = inStack.pop();
				outStack.push(element);
			}
		}
		if (outStack.isEmpty()) {
			return null;
		}
		return outStack.pop();
	}

	/**
     *  模拟入队操作：向inStack压入元素即可；
     *
	 * @param element
	 */
	public static void enQueue(int element) {
		inStack.push(element);
	}

}
