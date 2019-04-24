package io.nkcoder.alg.coding.array;

import java.util.HashMap;
import java.util.Map;

/**
 * leetcode: [Two Sum](https://leetcode.com/problems/two-sum/)
 * lintcode: [Two Sum](https://www.lintcode.com/en/problem/two-sum/)
 *

 Given an array of integers, return indices of the two numbers such that they add up to a specific target.
 You may assume that each input would have exactly one solution.

 Example:
    Given nums = [2, 7, 11, 15], target = 9,

    Because nums[0] + nums[1] = 2 + 7 = 9,
    return [0, 1].

 challenge, either of the following is acceptable:
    - O(n) Space, O(nlogn) Time
    - O(n) Space, O(n) Time

 * User: lingguo
 * Date: 14-8-4 下午10:11
 */
public class TwoSum {

    /**
     * 空间换时间： 使用哈希，遍历两次，第一次构建hash，第二次判断，注意两个数相等时，索引不能相同；
     * 时间：O(n), 空间O(n)
     *
     * @param nums
     * @param target
     * @return
     */
    private int[] twoSumByHashMap1(int[] nums, int target) {
        if (nums.length == 0) {
            return new int[] {-1, -1};
        }

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i]) && map.get(target - nums[i]) != i) {
                return new int[] {i, map.get(target - nums[i])};
            }
        }
        return new int[] {-1, -1};
    }

    /**
     * 空间换时间：哈希，只需一次遍历，遍历的时候判断互补的数是否在哈希map中，如果不在则加入map，否则返回结果
     * 时间：O(n), 空间：O(n)
     *
     * @param nums
     * @param target
     * @return
     */
    private int[] twoSumByHashMap2(int[] nums, int target) {
        if (nums.length == 0) {
            return new int[] {-1, -1};
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                int startIndex = map.get(target - nums[i]);
                int endIndex = i;
                return new int[] {startIndex, endIndex};
            }
            map.put(nums[i], i);
        }
        return new int[]{-1, -1};
    }

    /**
     * 还有一种思路： 空间换时间，先对数组排序，然后使用双指针，头尾相向遍历，但是要注意，排序后，元素的index要对应；
     * 时间：O(nlogn), 空间：O(1)
     * @param args
     */



    public static void main(String[] args) {
        TwoSum twoSum = new TwoSum();

        int[] nums = {11, 7, 4, 15};
        int target = 19;

        int[] resultByHashMap = twoSum.twoSumByHashMap1(nums, target);
        System.out.println(resultByHashMap[0] + ", " + resultByHashMap[1]);

        int[] resultBySort = twoSum.twoSumByHashMap2(nums, target);
        System.out.println(resultBySort[0] + ", " + resultBySort[1]);
    }
}
