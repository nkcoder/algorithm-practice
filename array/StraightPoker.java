package io.nkcoder.alg.coding.array;


/**
 * 问题描述：
 *  从扑克牌里抽出任意5张牌，其中J, Q, K,分表表示11, 12, 13，A表示1，大小王可以表示任意
 *  点数。求该5张牌是否可以构成顺子。
 *
 * 思路：
 *  大小王比较特殊，可以表示任意点数，我们用数字0来表示；
 *
 *  思路一：对所有的牌排序；统计0的个数，以及非0数字之间的间隙，如果间隙大于0的小于等于0的数量，则
 *  表示可以构成顺子。排序复杂度O(n)(复杂度只有针对大规模的问题时才有意思，比如本题最多只
 *  有14个数字O(nlogn)与O(n^2)是一个数量级的)；
 *
 *  思路二：0表示的大小王可以忽略；求非0数字的最大值与最小值，如果它们之间的差值小于5，则表示可以构成顺子；
 *  这里需要处理一个异常输入，即有重复的牌；如何去重：基于位运算构造签名；或者用数组模拟map；
 *
 * User: Daniel
 * Date: 14-1-19
 * Time: 下午11:32
 */
public class StraightPoker {
    private static final int MAX_SIZE = 14;

    /**
     * 检查抽出的n张牌是否可以构成顺子
     *
     * @param cards 表示抽出的牌的数组
     * @param length    抽出的牌的数量
     * @return  是否可以构成顺子
     */
    public static boolean verify(int[] cards, int length) {
        if (!isValidByBit(cards, length)) {
            return false;
        }

        // 求最大值和最小值
        int min = MAX_SIZE;
        int max = 0;
        for (int i = 1; i < length && cards[i] != 0; i++) {
            if (cards[i] < min) {
                min = cards[i];
            }
            if (cards[i] > max) {
                max = cards[i];
            }
        }

        // 计算差值
        if (max - min + 1 <= length) {
            return true;
        }
        return false;
    }

    /**
     * 基于位运算构造一个签名，判断数组中是否存在重复元素
     *
     * @param cards
     * @param length
     * @return
     */
    public static boolean isValidByBit(int[] cards, int length) {
        int mask = 0;
        for (int i = 0; i < length; i++) {
            if (cards[i] != 0) {
                if ((mask & (1 << cards[i])) != 0) {
                    return false;
                }
                mask |= (1 << cards[i]);
            }
        }
        return true;
    }

    /**
     * 使用辅助数组作为映射，判断原数组中是否存在重复元素；
     *
     * @param cards
     * @param length
     * @return
     */
    public static boolean isValidByArray(int[] cards, int length) {
        int[] buckets = new int[MAX_SIZE];
        for (int i = 0; i < length; i++) {
            if (buckets[cards[i]] != 0) {
                return false;
            }
            buckets[cards[i]] = 1;
        }
        return true;
    }
}