package io.nkcoder.alg.coding.stackqueue;


import java.util.LinkedList;

/**
 * 问题描述：
 *  实现一个栈，包含一个返回栈中最小元素的函数，
 *  要求入栈、出栈和返回最小值的时间复杂度均为O(1)
 *
 * 思路：
 *  使用一个辅助栈来保存栈的当前最小值。入栈时，将入栈的元素与辅助栈的栈顶（当前
 *  最小值）比较，如果比栈顶大，则表示最小值不变，将栈顶元素push到辅助栈里；如果
 *  入栈素比辅助栈的栈顶小，则更新最小值，则将该元素也push到辅助栈中。出栈时，每次
 *  都从辅助栈里弹出一个元素。
 *
 * User: lingguo
 * Date: 14-3-25
 * Time: 下午10:45
 */
public class StackWithMin {

	//  数据栈：存放数据；辅助栈：存储数据栈的当前最小值；
	private static LinkedList<Integer> dataStack = new LinkedList<Integer>();
	private static LinkedList<Integer> minStack = new LinkedList<Integer>();

	/**
	 *  入栈操作
	 *
	 * @param element   入栈元素
	 */
	public static void push(int element) {
		// 不需更新最小值，但最小值也需要入栈
		if (!minStack.isEmpty() && element > minStack.peek()) {
			minStack.push(minStack.peek());
		} else {
			minStack.push(element);     // 更新最小值
		}
		dataStack.push(element);       // 数据入栈
	}

	/**
	 * 出栈操作
	 *
	 * @return   数据栈的栈顶元素
	 */
	public static Integer pop() {
		//  数据栈和辅助栈都需要执行出栈操作
		if (!dataStack.isEmpty() && !minStack.isEmpty()) {
			minStack.pop();
			return dataStack.pop();
		}
		return null;
	}

	/**
	 *  获取当前栈的最小元素
	 *
	 * @return the min element
	 */
	public static Integer min() {
		//  辅助栈的栈顶元素即是
		if (!minStack.isEmpty()) {
			return minStack.peek();
		}
		return null;
	}

}

