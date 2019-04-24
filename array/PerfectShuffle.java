package io.nkcoder.alg.coding.array;

/**
 * 题目描述（完美洗牌）：
 *  有一个长度为2n的数组{a1a2...anb1b2..bn}, 希望排序后变为：{a1b1a2b2...anbn}，
 *  请考虑有无时间复杂度为O(n)，空间复杂度为O(1)的解法；
 *
 * 思路：（取n=4为例，即{a1a2a3a4b1b2b3b4} -> {a1b1a2b2a3b3a4b4})
 *   【思路一】：空间换时间：使用额外的数组，遍历原数组，依次将a1/b1/a2/b2/.../an/bn填入数组即可。
 *
 *  【思路二】：蛮力替换，通过元素的移动依次将b1、b2、b3、b4放到正确的位置：
 *      1.1 将b1放到正确的位置，则a2、a3、a4后移，b2放在a2的位置，即：
 *          a1a2a3a4b1b2b3b4 -> a1b1a2a3a4b2b3b4
 *      1.2 将b2放到正确的位置，则a3、a4后移，b2放在a3的位置，即：
 *          a1b1a2a3a4b2b3b4 -> a1b1a2b2a3a4b3b4
 *      1.3 将b3放到正确的位置，则a4后移，b3放在a4的位置，变换结束，即：
 *          a1b1a2b2a3a4b3b4 -> a1b1a2b2a3b3a4b4
 *      时间复杂度为O(n^2)，空间复杂度为O(1)；
 *
 *  【思路三】：时间复杂度为O(n)，空间复杂度为O(1)的解法参考：
 *      - https://github.com/nkcoder/The-Art-Of-Programming-By-July/blob/master/ebook/zh/02.09.md
 *
 * @author: lingguo
 * @time: 2014/8/31 8:54
 */
public class PerfectShuffle {
}
