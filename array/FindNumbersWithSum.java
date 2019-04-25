/**
 * 问题描述： 给定一个正整数s，求和为s的所有连续正整数序列（至少包含两个数），如s为15时，和为15的连续正整数序列有： 1+2+3+4+5，4+5+6,
 * 7+8。 输入：正整数s； 输出：和为s的所有连续正整数序列
 *
 * 思路： 要求连续序列之和，可以从最短最小的子序列入手（即1和2），求其和，如果和小于s，序列向元素大的方向扩展，如果大于s，从序列中剔除
 * 最小的元素，不断扩展，直到序列终止；那么，序列何时终止呢？因为序列至少两个数，两个数的和不能大于s，所以序列的最大边 界值即为s/2。
 *
 */
public class FindNumbersWithSum {

    /**
     * 给定值sum，求所有和为sum的连续整数序列
     * 
     * @param sum
     */
    public static void print(int sum) {
        int start = 1;
        int end = 2;
        int boundary = (sum + 1) >> 1; // 序列右边界
        while ((start < end) && (end <= boundary)) {
            int currentSum = 0;
            for (int i = start; i <= end; i++) {
                currentSum += i;
            }
            if (currentSum == sum) {
                System.out.println("find a sequence: start: " + start + ", end: " + end);
                end++;
            } else if (currentSum < sum) {
                end++;
            } else {
                start++;
            }
        }
    }

    public static void main(String[] args) {
        FindNumbersWithSum.print(15);
    }

    /**
     * * 同类问题： 给定递增数组data和数值s，在数组中寻找两个数，使其和为s，如果有多对符合条件的数对，都打印出来。
     * 思路：设置两个元素指针，一个指向数组的首元素，一个指向数组尾元素，求其和并与s比较，如果大于s，尾元素指针前移，否则，
     * 首元素指针后移，直到两个指针元素相遇；
     *
     * @param data 递增数组
     * @param size 数组的大小
     * @param sum  数字之和
     */
    public void findPairSum(int[] data, int size, int sum) {
        int start = 0;
        int end = size - 1;

        while (start < end) {
            int currentSum = data[start] + data[end];
            if (currentSum == sum) {
                System.out.println("findParentByPath pair: {" + data[start] + ", " + data[end] + "}");
                start++;
                end--;
            } else if (currentSum < sum) {
                start++;
            } else {
                end--;
            }
        }

    }
}
