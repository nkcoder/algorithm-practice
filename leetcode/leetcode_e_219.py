"""
@link: [219. Contains Duplicate II](https://leetcode.com/problems/contains-duplicate-ii/)

@desc:

    Given an array of integers and an integer k, find out whether there are two distinct indices i and j in the array such that nums[i] = nums[j] and the difference between i and j is at most k.

"""


import time
class Solution(object):

    def contains_near_by_duplicate1(self, nums, k):
        """
        use dict
        @Runtime: 48 ms

        :type nums: List[int]
        :type k: int
        :rtype: bool
        """
        if not nums or k <= 0:
            return False

        num_dict = {}
        for index, value in enumerate(nums):
            if value in num_dict and index - num_dict[value] <= k:
                return True

            num_dict[value] = index

        return False

    def contains_near_by_duplicate2(self, nums, k):
        """
        use dict, by keep a window of size k;
        **Time Limit Exceeded** (Java version is Accepted)

        :param nums:
        :param k:
        :return:
        """
        if not nums or k <= 0:
            return False

        num_dict = {}
        for index, value in enumerate(nums):
            if value in num_dict.values():
                return True
            else:
                num_dict[index] = value

            if index > k:
                del num_dict[index-k-1]

        return False

if __name__ == '__main__':
    solution = Solution()

    assert solution.contains_near_by_duplicate1(None, 0) == False
    assert solution.contains_near_by_duplicate1([], 0) == False
    assert solution.contains_near_by_duplicate1([1, 3, 2], 1) == False
    assert solution.contains_near_by_duplicate1([1, 3, 2, 3, 5], 2) == True
    assert solution.contains_near_by_duplicate1([1, 4, 6, 5, 3, 2, 4, 8], 3) == False
    assert solution.contains_near_by_duplicate1([1, 4, 3, 2, 6, 8, 1, 2, 1], 2) == True

    nums_list = [1, 5, 7, 9, 3, 2, 1, 3, 12, 13, 15, 21]
    k = 5
    start_time = time.time()


    start_time = time.time()
    print('result1: {0}, time1: {1}'.format(solution.contains_near_by_duplicate1(nums_list, k), time.time() - start_time))

    print('result2: {0}, time2: {1}'.format(solution.contains_near_by_duplicate2(nums_list, k), time.time() - start_time))


