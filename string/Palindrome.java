package io.nkcoder.alg.coding.string;



/**
 * 问题描述：
 *  判断一个字符串是否为回文串，即字符串顺着读和反着读是一样的，如：madam是一个回文串。
 *
 * 思路：
 *  思路一：使用两个指针，从两端向中间遍历，直到相遇，判断是否相等；时间复杂度O(n)，空间复杂度O(1)；
 *  思路二：使用两个指针，从中间向两端遍历，直到遇到字符边界，判断是否相等；时间复杂度O(n)，空间复杂度O(1)；
 *
 * 链接：https://github.com/nkcoder/The-Art-Of-Programming-By-July/blob/master/ebook/zh/01.04.md
 *
 * User: lingguo
 * Date: 14-7-27 下午11:38
 */
public class Palindrome {

    /**
     * 判断字符串是否为回文；
     * 思路一：使用两个指针，从两端向中间遍历；
     *
     * @param str
     * @return
     */
    public static boolean checkStr(char[] str) {
        if (str == null || str.length == 0) {
            return false;
        }
        /* 确定指针的初始位置 */
        int first = 0;
        int last = str.length - 1;
        while (first < last) {
            if (str[first] != str[last]) {
                return false;
            }
            first++;
            last--;
        }
        return true;
    }

/**********************************************************************************************************/

    /**
     * 判断字符串是否为回文：
     * 思路二：使用两个指针，从中间向两端遍历；
     *
     * @param str
     * @return
     */
    public static boolean checkStr2(char[] str) {
        if (str == null || str.length == 0) {
            return false;
        }
        if (str.length == 1) {
            return true;
        }
        /**
         * 确定指针的首尾位置，这里没有判断str长度的奇偶，因为如果是奇数，
         * 最中间的对称字符不用比较，直接忽略了。
          */
        int midLeft = (str.length >> 1) - 1;
        int midRight = str.length - 1 - midLeft;
        while (midLeft >= 0) {
            if (str[midLeft] != str[midRight]) {
                return false;
            }
            midLeft--;
            midRight++;
        }
        return true;
    }

/****************************************************************************************/

}
