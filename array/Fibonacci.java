
/**
 * 问题描述： 求Fibonacci序列之和：f(n) = f(n-1) + f(n-2); 【n>0, f(1)=f(2)=1】
 *
 * 思路： 思路一. 根据公式递归求解，会有大量重复计算，效率低；复杂度（n的指数级） 思路二. 保存中间结果，迭代累加： f(3) = f(1) +
 * f(2); f(1)和f(2)是已知的； f(4) = f(2) + f(3);
 * f(2)和f(3)是已知的，可以将f(2)看作原来的f(1)，将f(3)看作原来的f(2)； ... 逐步累加即可，时间复杂度O(n);
 *
 */
public class Fibonacci {

	/**
	 * 迭代求和的思路
	 *
	 * @param n
	 * @return
	 */
	public static int calculate(int n) {
		if (n < 0) {
			return 0;
		}
		if (n <= 2) {
			return 1;
		}

		int f1 = 1;
		int f2 = 1;
		int f = 0;
		for (int i = 3; i <= n; i++) {
			f = f1 + f2;
			f1 = f2;
			f2 = f;
		}
		return f;
	}

	public static void main(String[] args) {
		int n = 10;
		int result = calculate(n);
		System.out.print("result: " + result);
	}
}

/*
 * 同类题目： 1. 青蛙跳台阶：一只青蛙一次可以跳一级台阶，也可以跳2级台阶，请问跳n级台阶，一共有多少种跳法？
 *
 * 思路： n级是f(n)，第一次跳一级，后面n-1级，f(n-1)；第一次跳二级，后面n-2级，f(n-2)，即满足： f(n) = f(n-1) +
 * f(n-2) 【f(1) = 1, f(2) = 1, n > 0】
 *
 * 2. 矩形覆盖：我们可以使用2x1的矩形去横着或者竖着去覆盖更大的矩形。请问，用8个2x1的矩形无重叠地覆盖 一个2x8的大矩形，总共有多少种方法？
 *
 * 思路： 覆盖2x8的矩形的方法记为f(8)，如果横着覆盖，剩下的为f(7)，如果竖着覆盖，剩下的为f(6)，所以一共为： f(8) = f(7) +
 * f(6)
 */