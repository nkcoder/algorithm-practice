package io.nkcoder.alg.coding.array;


/**
 * 问题描述：
 *  计算 pow(double base, int ex)，即base的ex次方；
 *  忽略大数问题，不得使用库函数
 *
 * 思路：
 *  题目不难，但需要注意几个问题：
 *  1. 如果ex为负数，需要注意分母为0的特殊处理；
 *  2.浮点数的比较问题；
 *  3. 求幂次方时的优化，二分平方的思路；
 *
 * User: Daniel
 * Date: 13-12-18
 * Updated: 16-09-06
 * Time: 下午11:30
 */
public class PowOfDouble {

	private final double THRESHOLD = 1E-6;

	/**
	 * 计算浮点数base的exponent次幂，不考虑大数（溢出）问题
     *
	 * @param base
	 * @param exponent
	 * @return  -1表示异常，否则，返回值为结果值
	 */
	public Double calculate(double base, int exponent) {
		if (isEqual(base, 0.0)) {
			if (exponent < 0) {
				return null;
			}
			return 1.0;
		}

		// 计算exponent为正值时的结果
		int absExponent = exponent;
		if (exponent < 0) {
			absExponent = -exponent;
		}

        // 递归计算幂次方
		double result = calPow1(base, absExponent);

		// 如果exponent为负值，取其倒数
		if (exponent < 0) {
			result = 1.0 / result;
		}
		return result;
	}

	/**
	 * 【思路一】计算base的ex次方，其中ex为整数，二分法，地推实现：
	 *  如果指数为偶数, b^e = b^(e/2) * b^(e/2)
	 *  如果指数为奇数, b^e = b^(e/2) * b^(e/2) * b
	 *  这比通过循环求幂值的效率要好一些，可以通过递归来实现。
     *
	 * @param base
	 * @param ex
	 * @return
	 */
	public static double calPow1(double base, int ex) {
		if (ex == 1) {
			return base;
		}
		double result = calPow1(base, ex >> 1);
		result *= result;               // 先计算平方
		if ((ex & 0x1) == 1) {   // 如果指数exponent为奇数，平方外应该再乘以base
			result *= base;
		}
		return result;
	}

    /**
     * 【思路二】计算base的ex次方，ex为正数，递归的另一种实现方式，更直观
     *
     * @param base
     * @param ex
     * @return
     */
    public static double calPow2(double base, int ex) {
        if (ex == 1) {
            return base;
        }
        // 根据奇偶分支
        if ((ex & 1) == 1) {
            return calPow2(base, ex >> 1) * calPow2(base, ex >> 1) * base;
        } else {
            return calPow2(base, ex >> 1) * calPow2(base, ex >> 1);
        }
    }

	/**
	 * 比较两个浮点数是否相等
     *
	 * @param d1
	 * @param d2
	 * @return 如果相等，返回true，否则返回false；
	 */
	private boolean isEqual(double d1, double d2) {
		if ((d1 - d2) < THRESHOLD && (d1 - d2) > -THRESHOLD) {
			return true;
		}
		return false;
	}

}
