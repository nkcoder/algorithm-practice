package io.nkcoder.alg.coding.array;

/**
 * 题目描述：
 *  数组中有一个数字出现的次数超过了数组长度的一半，请找出这个数字；
 *
 * 思路：
 *  【思路一】：将数组排序，比如使用快速排序，则数组最中间的元素即为所求
 *      时间复杂度为O(nlogn)；
 *  【思路二】：使用HashMap，将数组的每一个元素作为key，出现的次数作为value，
 *      统计每个元素出现的次数，返回出现次数超过一半的元素即可；
 *      时间复杂度O(n)，空间复杂度O(n)，相当于以空间换时间；
 *  【思路三】；每次从数组中删除两个不相同的元素(无论是否是要求的元素)，则每次删除
 *      后出现次数超过一半的元素在剩余的数组中的出现次数仍然超过一半，则最后剩下的元素
 *      即为所求；时间复杂度O(n)；
 *  【思路四】：与【思路三】的思路是一样的，只不过在实现上比较巧妙：使用一个整数保存
 *      之前出现频繁的元素，另外使用一个表示次数的计数器，如果当前元素与保存的元素相等，
 *      则计数器加1，否则计数器减1，如果计数器为0，则更新保存的值，并将计数器的值重置
 *      为1，则最后保存的值即为所求；
 *      时间复杂度为O(n);
 *
 * @author: lingguo
 * @time: 2014/8/31 12:46
 */
public class MoreThanHalfNumber {

    public static int find(int[] data, int n) {
        int last = data[0];
        int times = 1;

        for (int i = 1; i < n; i++) {
            if (data[i] == last) {
                times++;
            } else {
                if (times == 0) {
                    times  = 1;
                    last = data[i];
                } else {
                    times--;
                }
            }
        }
        return last;
    }

    public static void main(String[] args) {
        int[] data = {1, 2, 2, 1, 3, 1, 4, 5, 1, 1, 1};
        int num = find(data, data.length);
        System.out.println("result: " + num);
    }
}
