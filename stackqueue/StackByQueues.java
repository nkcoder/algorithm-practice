package io.nkcoder.alg.coding.stackqueue;

import java.util.LinkedList;

/**
 *
 * 问题描述：
 *  使用两个队列实现栈的入栈和出栈操作；
 *
 * 思路：
 *  始终维持一个队列为空，一个队列非空；入队的时候向非空的队列插入一个元素；出队的时候，将非空
 *  队列中的所有元素（除队尾元素外，此元素为栈顶元素）都依次出队并插入到空队列中，此时非空队列中的
 *  为一个的队尾元素即为要出栈的元素。
 *
 * Usr: Daniel
 * Date: 13-12-15
 * Time: 上午11:19
 */
public class StackByQueues {
	private static LinkedList<Integer> firstQueue = new LinkedList<>();
	private static LinkedList<Integer> secondQueue = new LinkedList<>();

	/**
	 *  模拟入栈操作：向非空队列插入一个元素
	 *
	 * @param element
	 */
	public static void push(Integer element) {
		if (firstQueue.isEmpty()) {
			secondQueue.offer(element);
		} else {
			firstQueue.offer(element);
		}
	}

	/**
	 *  出栈：将非空队列除尾元素外的所有元素都出队并入队到另一个空队列中。
	 *
	 * @return
	 */
	public static Integer pop() {
		if (firstQueue.isEmpty() && secondQueue.isEmpty()) {
			return null;
		}
		if (firstQueue.isEmpty()) {
			while (secondQueue.size() != 1) {
				int element = secondQueue.poll();
				firstQueue.offer(element);
			}
			return secondQueue.poll();
		}
		if (secondQueue.isEmpty()) {
			while (firstQueue.size() != 1) {
				int element = firstQueue.poll();
				secondQueue.offer(element);
			}
			return firstQueue.poll();
		}
		return null;
	}

}