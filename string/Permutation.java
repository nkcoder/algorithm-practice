package io.nkcoder.alg.coding.string;

/**
 *  * 问题描述：
 *  输出字符串的全排列；
 *
 * 思路：
 *  【思路一】：交换并递归的策略，即固定首字符，求剩下所有字符的全排列，排列完毕，将每一个字符与首字符交换，
 *  继续求首字符之外的字符串的全排列。如：求abc，a不变，剩下子串bc的全排列为bc、cb，则有全排列abc、acb；然
 *  后a与b互换，剩下子串ac的全排列为ac、ca，此时全排列为bac、bca；然后a与c互换，剩下子串bc全排列为bc、cb，
 *  至此，所有全排列求出；
 *
 *  【思路二】：使用字典序的概念，类似于两个字符串比较，两个字符串根据字符从前向后比较，如果字符串A的某一位大于
 *  字符串B的对应位，则称A>B，如果A和B对应字符都相等，但A比B长，则A>B，如abcd < abdc, abcd < abcde;利用字典
 *  徐，求当前字符串的下一个排列，如果不存在下一个排列，则表示全排列列举完毕。例如：求字符串"adbqsrih"的下一个排
 *  列：
 *      - 首先，寻找最后一个可以增长的位，即从后向前遍历字符串，寻找第一个字符，使得比它右侧的字符小，如上例中的q
 *      q比s小，所以q是最后一个可增长的位；
 *      - 然后，在q的右边寻找大于q的最小的位，如上例为r；
 *      - 将q和r交换，得到adbrsqih;
 *      - 将r为后的字符串翻转，即最小化排列，得到adbrhiqs;(为什么是翻转？因为此时右侧的字符串是逆序排序的)
 *  复杂度：两个算法均是O(n!)
 *
 * 参考：
 * - https://github.com/nkcoder/The-Art-Of-Programming-By-July/blob/master/ebook/zh/01.06.md
 *
 * User: lingguo
 * Date: 14-7-30 下午8:51
 */
public class Permutation {

    /**
     * 首字符互换和递归实现全排列
     *
     * @param str
     * @param begin
     * @param end
     */
    public void swapAndRecur(char[] str, int begin, int end) {
        // 已经递归到最后一个字符，为一个全排列，输出并返回
        if (begin == end) {
            System.out.println(str);
            return;
        }
        // 将首字符与其它每一个字符互换，递归求全排列
        for (int i = begin; i <= end; i++) {
            swap(str, i, begin);
            swapAndRecur(str, begin + 1, end);
            swap(str, i, begin);
        }
    }


    /**
     * 计算当前字符的“下一个排列”
     * 注意：循环调用本方法得到的全排列集合不包括原字符本身，因此原字符本身需要单独输出
     *
     * @param str
     * @return
     */
    public boolean hasNextPerm(char[] str) {
        /**
         * 寻找最后一个可以增长的位
         */
        int i = str.length - 2;
        while (i >= 0 && str[i] >= str[i+1]) {
            i--;
        }
        // 已经是最大排列，全排列完毕
        if (i < 0) {
            return false;
        }

        /**
         * 寻找增长位之后，大于增长位的值的最小值
         */
        int j = str.length - 1;
        while (j > i && str[j] <= str[i]) {
            j--;
        }

        /**
         * 互换并翻转
         */
        swap(str, i, j);
        swap(str, i + 1, str.length - 1);
        System.out.println(str);

        return true;
    }

    private void swap(char[] str, int i, int j) {
        char tmp = str[i];
        str[i] = str[j];
        str[j] = tmp;
    }
}
