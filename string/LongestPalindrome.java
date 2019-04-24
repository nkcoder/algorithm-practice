package io.nkcoder.alg.coding.string;

/**
 * 问题描述：
 *  给定一个字符串，求最长回文子串；
 *
 * 思路：
 *  【思路一】；遍历每一个字符，以该字符为中心，向两端扩展形成回文串，遍历完毕，取最大值即可；
 *  这里就是需要分两种情况进行讨论：回文串为偶数和奇数。
 *  时间复杂度为O(n^2)；
 *  参考：
 *      https://github.com/nkcoder/The-Art-Of-Programming-By-July/blob/master/ebook/zh/01.05.md
 *
 *  【思路二】：Manacher算法
 *  - 在每个字符的前面增加一个固定字符，如'#'，则可以屏蔽奇偶的讨论，如字符串为"abcdcba", 增加字符后变为：
 *  "^#a#b#c#d#c#b#a#$"，其中首尾的$和^用于边界判断；
 *  - 利用对称性，假定已知最长回文串的索引c和p[c]，则其左右边界分为为L=c-p[c], R=c+p[c]，以右边界为基准；
 *  如果遍历到的当前字符为i，其回文长度可以先尝试从关于其对称点2c-i获取(因为其对称点已经被遍历过了，对称长
 *  度是已知的)，分情况讨论如下：
 *      - 如果i < R，即i在c的右边界之内，此时其对称点2c-i在左边界之内，则此时i在R之内的部分的回文长度为：R-i和
 *      p[2c-i]的较小值，R之外的部分在此基础上累加；(这部分需要画图帮助理解)
 *      - 如果 i >= R，即i在c的边界之外，则对称性无法使用，只能挨个遍历；
 *
 *  参考：
 *  1. http://leetcode.com/2011/11/longest-palindromic-substring-part-ii.html
 *
 * User: lingguo
 * Date: 14-7-28 下午10:19
 */
public class LongestPalindrome {

    /**
     * 思路一：
     * 遍历每一个字符，计算以该字符为中心的最长回文串，最后求最大值；这里需要区分
     * 奇数和偶数；
     * 时间复杂度O(n^2)
     *
     * @param str
     * @return
     */
    public static char[] center(char[] str) {
        if (str == null || str.length == 0) {
            return null;
        }

        int start = 0;
        int end = 0;
        int max = 1;

        for (int i = 0; i < str.length; i++) {
            int j = 0;

            /* 回文串为奇数，str[i]为中心，左右字符比对 */
            for (j = 0; i - j >= 0 && i + j <= str.length - 1; j++) {
                if (str[i - j] != str[i + j]) {
                    break;
                }
            }
            /* 注意：j-1才是回文一半的数量，因为为j时已经退出 */
            if (2 * (j-1) + 1 > max) {
                max = 2 * (j-1) + 1;
                start = i - j + 1;
                end = i + j - 1;
            }

            /* 回文串为偶数，str[i]作为左字符，与右字符str[i+1]比对 */
            for (j = 0; i - j >= 0 && i + j + 1 <= str.length - 1; j++) {
                if (str[i-j] != str[i+j+1]) {
                    break;
                }
            }
            if (2 * (j-1) + 2 > max) {
                max = 2*(j-1) + 2;
                start = i - j + 1;
                end = i + j;
            }
        }

        /* 保存回文串 */
        char[] longestPal = new char[max];
        for (int i = start, j = 0; i <= end; i++) {
            longestPal[j++] = str[i];
        }

        return longestPal;
    }


    /**
     * 思路二：使用manacher算法
     *  主要是理解对称性对于求解的帮助；在遍历时会维护一个当前最长回文串，
     *  如果当前访问的字符，在该回文串边界之内，则可以利用对称性，利用其对称
     *  点的回文信息，减少回文比较的次数；如果不在回文串的边界之内，则只能顺序
     *  比较。
     *
     * @param src
     * @return
     */
    public static char[] manacher(char[] src) {
        // 预处理，增加辅助字符，屏蔽奇偶讨论
        char[] dest = preProcess(src);
        int len = dest.length;
        int maxIndex = 0;
        int rightBorder = 0;
        // 保存每个字符的回文长度(其实是整个回文串一半的长度，不包含当前字符)
        int[] pal = new int[len];

        for (int i = 1; i < len - 1; i++) {
            // 对称点
            int iMirror = 2 * maxIndex - i;
            // 当前访问的字符在右边界R之内，利用对称点的回文信息
            if (rightBorder > i && iMirror >= 0) {
                pal[i] = Math.min(rightBorder - i, pal[iMirror]);
            } else {
                pal[i] = 0;     // 右边界之外，挨个遍历
            }
            // 由于增加了边界符，不用判断越界
            while (dest[i - pal[i] - 1] == dest[i + pal[i] + 1]) {
                pal[i]++;
            }
            // 更新最大回文串的索引和右边界
            if (i + pal[i] > rightBorder) {
                rightBorder = i + pal[i];
                maxIndex = i;
            }
        }

        // 求最大回文长度
        int centerIndex = 0;
        int maxLen = 0;
        for (int j = 1; j < len - 1; j++) {
            if (pal[j] > maxLen) {
                maxLen = pal[j];
                centerIndex = j;
            }
        }

        // 求回文串并返回
        int startIndex = (centerIndex -maxLen - 1) >> 1;
        char[] ret = new char[maxLen];
        int j = 0;
        for (int i = startIndex; i < maxLen; i++) {
            ret[j++] = src[i];
        }

        return ret;
    }


    /**
     * 对字符串进行预处理，每个字符前增加#，并在首尾增加边界字符
     *
     * @param src
     * @return
     */
    public static char[] preProcess(char[] src) {
        if (src == null || src.length == 0) {
            return null;
        }
        char[] dest = new char[src.length * 2 + 3];
        int i = 0;
        dest[i++] = '^';
        for (char c: src) {
            dest[i++] = '#';
            dest[i++] = c;
        }
        dest[i++] = '#';
        dest[i] = '$';
        return dest;
    }

}
