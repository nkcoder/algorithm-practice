"""
@link: [27. Remove Element](https://leetcode.com/problems/remove-element/)

@desc:

    Given an array and a value, remove all instances of that value in place and return the new length.

    The order of elements can be changed. It doesn't matter what you leave beyond the new length.
"""


class Solution(object):
    def remove_element(self, nums, val):
        """
        **NOTE**: you have to remove all instances of that value in place and return
        the new length
        @Runtime: 44 ms

        :type nums: List[int]
        :type val: int
        :rtype: int
        """
        if nums is None:
            return 0
        if val is None:
            return len(nums)

        while val in nums:
            nums.remove(val)
        return len(nums)

if __name__ == '__main__':
    solution = Solution()
    nums1 = [4, 5]
    val1 = 4

    len1 = solution.remove_element(nums1, val1)
    print('len1: {0}'.format(len1))
