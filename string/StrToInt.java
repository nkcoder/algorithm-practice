package io.nkcoder.alg.coding.string;

/**
 * 问题描述：
 *  将字符串转换为整数；
 *
 * 思路：
 *  主要是注意一些特殊的正常情况和异常情况的处理：
 *  - 正常：
 *    - 带正负号；
 *    - 有前缀空格；
 *  - 异常：
 *    - 字符串为空或null；
 *    - 字符串包含非数字字符；
 *    - 由于溢出导致无法转换；
 *    - 字符串仅包含空白符或者符号，如"  +", "   "等；
 *  - 如何处理溢出：
 *    - 如果直接与MAX_VALUE或MIN_VALUE比较，由于此时溢出已经发生，所以无法处理；因此将乘法转化为除法，
 *    如处理向上溢出，计算到当前字符时，有：result * 10 + digit，此时判断result与MAX_VALUE/10，如果
 *    大于，则溢出；如果等于，再比较digit与MAX_VALUE/%10，如果也大于，则表示溢出；向下溢出是同样的处理
 *    方式。
 *
 * User: lingguo
 * Date: 14-7-26 下午4:13
 */
public class StrToInt {

    public static Integer convert(char[] str) {
        if (str == null || str.length == 0) {
            throw new IllegalArgumentException("null");
        }

        boolean negative = false;
        int i = 0, len = str.length;
        while (i < len && str[i] == ' ') {
            i++;
        };
        // 空白串，非法
        if (i == len) {
            throw new IllegalArgumentException("blank string.");
        }
        // 判断正负号
        if (str[i] == '-') {
            negative = true;
            i++;
        } else if (str[i] == '+') {
            i++;
        }
        // 仅有正负号，非法
        if (i == len) {
            throw new IllegalArgumentException("only sign.");
        }

        int result = 0;
        while (i < len) {
            if (str[i] >= '0' && str[i] <= '9') {
                int digit = str[i] - '0';
                // 正数溢出，因为乘法无法处理溢出，使用除法间接处理
                if (!negative && (result > Integer.MAX_VALUE/10 || (result == Integer.MAX_VALUE/10 && digit >
                        Integer.MAX_VALUE%10))) {
                    throw new IllegalArgumentException("overflow.");
                }
                // 负数溢出
                if (negative && (-result < Integer.MIN_VALUE/10 || (-result == Integer.MIN_VALUE/10 && digit > Integer
                        .MIN_VALUE%10))) {
                    throw new IllegalArgumentException("underflow.");
                }
                result = result * 10 + digit;
            } else {
                throw new IllegalArgumentException("malformed string.");
            }
            i++;
        }
        return negative ? -result : result;
    }
}
