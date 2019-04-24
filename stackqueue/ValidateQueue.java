package io.nkcoder.alg.coding.stackqueue;

import java.util.LinkedList;

/**
 * 问题描述：
 *  两个整数序列，第一个表示栈的压入顺序（压入栈的元素可以出栈），请判断第二个序列是否可以是栈的一个弹出序列。
 * (假设压入序列的数字均不相同）
 *
 * 思路：
 *   既然是模拟入栈和出栈，我们需要一个中间辅助栈；出栈序列中的元素首先与当前栈的栈顶比较，如果相等，则出栈，
 *   继续下一个元素；如果与栈顶元素不等，则与输入序列相比，不等，则入栈，直到相等，则继续出栈序列中的下一个
 *   元素。最后，如果出栈序列、辅助栈和入栈序列均为空，则表示出栈序列为合法序列；复杂度O(n);
 *
 * User: lingguo
 * Date: 14-3-26
 * Time: 下午9:27
 */
public class ValidateQueue {
	/**
	 *  第一个是入栈序列，判断第二个是否可以为一个出栈序列；
     *   除了入栈序列和出栈序列，需要一个中间辅助栈；
	 *
	 * @param input  入栈序列
	 * @param output   出栈序列
	 * @return   如果是合法的出栈序列，返回true，否则返回false；
	 */
	public static boolean check(int[] input, int[] output) {
		//  辅助栈
		LinkedList<Integer> stack = new LinkedList<>();

		//  出栈序列和入栈序列的元素索引
		int inputIndex = 0;
		int outputIndex = 0;
		//  遍历出栈序列
		for (outputIndex = 0; outputIndex < output.length; outputIndex++) {
			//  首先和中间栈的栈顶相比，如果相等，则弹出栈顶元素，同时，出栈序列后移
			if (!stack.isEmpty() && stack.peek() == output[outputIndex]) {
                stack.pop();
                continue;
			}

			//  然后与入栈序列相比，如果不等，则入栈，直到相等，表示当前元素合法（入栈立即出栈即可）
			while (inputIndex < input.length && output[outputIndex] != input[inputIndex]) {
				stack.push(input[inputIndex]);
				inputIndex++;
			}
			if (inputIndex == input.length) {
					return false;
			}
			//  别忘了将入栈序列的索引后移
			inputIndex ++;
		}
		// 合法序列的条件
		return (inputIndex == input.length && outputIndex == output.length && stack.isEmpty());
	}
}
