"""
@link: [283. Move Zeroes](https://leetcode.com/problems/move-zeroes/)

@desc:

    Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.

    For example, given nums = [0, 1, 0, 3, 12], after calling your function, nums should be [1, 3, 12, 0, 0].

    Note:
    You must do this in-place without making a copy of the array.
    Minimize the total number of operations.
"""


class Solution(object):
    def move_zeroes(self, nums):
        """
        use two index p and q, p always points to the first zero, q always points to the first non-zero,
        swap them and iterate.
        @Runtime: 76 ms

        :type nums: List[int]
        :rtype: void Do not return anything, modify nums in-place instead.
        """
        if not nums:
            return
        if 0 not in nums:
            return

        # find the first element with value 0
        for zero_index in range(len(nums)):
            if nums[zero_index] == 0:
                break

        # find the first element with value not 0
        for nonzero_index in range(zero_index, len(nums)):
            if nums[nonzero_index] != 0:
                break

        # swap and repeat
        while zero_index < nonzero_index < len(nums):
            nums[zero_index], nums[nonzero_index] = nums[nonzero_index], nums[zero_index]
            while zero_index < len(nums):
                if nums[zero_index] == 0:
                    break
                zero_index += 1

            while nonzero_index < len(nums):
                if nums[nonzero_index] != 0:
                    break
                nonzero_index += 1

            if nonzero_index == len(nums):
                break

    def move_zeroes2(self, nums):
        """
        the same as the first solution, use enumerate to iterate list
        @Runtime: 508 ms

        :type nums: List[int]
        :rtype: void Do not return anything, modify nums in-place instead.
        """
        if not nums:
            return
        if 0 not in nums:
            return

        # find the first element with value 0
        i = 0
        for (index, num) in enumerate(nums):
            if num == 0:
                i = index
                break

        # find the first element with value not 0
        j = i
        for (index, num) in enumerate(nums[i:], start=i):
            if num != 0:
                j = index
                break

        # swap and repeat
        while i < j < len(nums):
            nums[i], nums[j] = nums[j], nums[i]

            for (index1, num1) in enumerate(nums[i:], start=i):
                if num1 == 0:
                    i = index1
                    break
            for (index2, num2) in enumerate(nums[j:], start=j):
                if num2 != 0:
                    j = index2
                    break
            if index2 == len(nums) - 1 and num2 == 0:
                break


if __name__ == '__main__':
    solution = Solution()
    nums = [0, 0, 1, 2, 0]
    solution.move_zeroes(nums)

    print('nums: {0}'.format(nums))
