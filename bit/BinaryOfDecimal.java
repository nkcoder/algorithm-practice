package io.nkcoder.alg.coding.bit;


/**
 * 问题描述：
 *  将一个以String表示的浮点数（如3.72），转换为二进制表示；如果该浮点数无法使用二进制
 *  精确表示，则输出"ERROR"
 *
 * 思路：
 *  将浮点数分为整数和小数两部分，整数部分循环求余；
 *  小数部分如何表示？其实0.75 = 0.5 + 0.25 = 1*(2^-1) + 1*(2^-2)，其二进制表示即为0.11；
 *  左移1位后：1.5 = 1*2^0 + 1*2^-1 = 1.1，此时浮点值大于1，左移后该位为1，即1*2^0，如果
 *  左移后浮点值小于1，如0.35，则左移后该二进制位为0，即0*2^0；
 *  循环左移，判断值是否大于1，确定每一个二进制位；
 *  分析：假设浮点数为0.abcd, 对应的二进制表示为0.xyzt，因为浮点数小于1，所以二进制表示小数点左边
 *  为0；现在将浮点数乘以2，二进制表示左移1位，变成了x.yzt，如果浮点数大于1，则二进制整数部分为x*2^0，
 *  所以x为1，同理，如果乘2后浮点数小于1，则x为0。
 *
 * User: lingguo
 * Date: 14-7-12
 */
public class BinaryOfDecimal {

    /**
     * 将浮点数表示为二进制形式
     *
     * @param decimalValue
     * @return
     */
    public static String represent(String decimalValue) {
        // 因为.在正则表达式里表示匹配任意单个字符，所以需要先转义
        String[] valueArray = decimalValue.split("\\.");
        int intPart = Integer.valueOf(valueArray[0]);
        double decPart = Double.valueOf(decimalValue) - intPart;

        // 整数部分的二进制表示：循环求余
        String intBinary = "";
        while (intPart != 0) {
            int r = intPart % 2;
            intPart = intPart >> 1;
            intBinary = r + intBinary;
        }

        // 小数部分：左移位与1比较
        String decBinary = "";
        while (decPart > 0) {
            decPart = decPart * 2;
            if (decPart >= 1) {
                decBinary = decBinary + 1;
                decPart = decPart - 1;
            } else {
                decBinary = decBinary + 0;
            }

            // 无法使用二进制精确表示
            if (decBinary.length() > 32) {
                System.out.println("ERROR");
                break;
            }
        }

        return intBinary + "." + decBinary;
    }
}
