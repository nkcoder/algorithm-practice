"""
@link: [217. Contains Duplicate](https://leetcode.com/problems/contains-duplicate/)

@desc:

    Given an array of integers, find if the array contains any duplicates. Your function should return true if any value appears at least twice in the array, and it should return false if every element is distinct.

"""


class Solution(object):

    def contains_duplicate_by_loop(self, nums):
        """
        by loop
        **Time Limit Exceeded**

        :type nums: List[int]
        :rtype: bool
        """

        if not nums:
            return False

        for index, value in enumerate(nums[1:], start=1):
            if value in nums[:index]:
                return True

        return False

    def contains_duplicate_by_sort(self, nums):
        """
        sort first and then traverse
        @Runtime: 52 ms

        :param nums:
        :return:
        """
        if not nums:
            return False

        sorted_nums = sorted(nums)
        for index, value in enumerate(sorted_nums[1:], start=1):
            if value == sorted_nums[index - 1]:
                return True

        return False

    def contains_duplicate_by_dict(self, nums):
        """
        use set or dict, time-space tradeoff, best solution
        @Runtime: 48 ms

        :param nums:  List[int]
        :return:  bool
        """
        if not nums:
            return False

        nums_set = set()
        for value in nums:
            if value in nums_set:
                return True
            else:
                nums_set.add(value)

        return False


if __name__ == '__main__':

    solution = Solution()

    assert solution.contains_duplicate_by_loop([]) == False
    assert solution.contains_duplicate_by_loop(None) == False
    assert solution.contains_duplicate_by_loop([1, 2, 3, 4, 5]) == False
    assert solution.contains_duplicate_by_loop([1]) == False
    assert solution.contains_duplicate_by_loop([1, 2, 3, 1, 5, 3]) == True

    assert solution.contains_duplicate_by_sort([]) == False
    assert solution.contains_duplicate_by_sort(None) == False
    assert solution.contains_duplicate_by_sort([1, 2, 3, 4, 5]) == False
    assert solution.contains_duplicate_by_sort([1]) == False
    assert solution.contains_duplicate_by_sort([1, 2, 3, 1, 5, 3]) == True

    assert solution.contains_duplicate_by_dict([]) == False
    assert solution.contains_duplicate_by_dict(None) == False
    assert solution.contains_duplicate_by_dict([1, 2, 3, 4, 5]) == False
    assert solution.contains_duplicate_by_dict([1]) == False
    assert solution.contains_duplicate_by_dict([1, 2, 3, 1, 5, 3]) == True