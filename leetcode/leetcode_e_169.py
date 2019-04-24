"""
@link: 

    [169. Majority Element](https://leetcode.com/problems/majority-element/)

@desc:

    Given an array of size n, find the majority element. The majority element 
    is the element that appears more than ⌊n/2⌋ times.

    You may assume that the array is non-empty and the majority element always 
    exist in the array.

"""


class Solution(object):

    def majority_element_by_dict(self, nums):
        """
        use dict to record count of each num.
        @Runtime: 64 ms

        :param nums:
        :return:
        """
        if len(nums) == 1:
            return nums[0]

        count_dict = {}
        majority_count = len(nums) // 2

        for num in nums:
            if num in count_dict:
                count_dict[num] += 1
                if count_dict[num] > majority_count:
                    return num
            else:
                count_dict[num] = 1

        # not found
        return None

    def majority_element_by_stack(self, nums):
        """
        by stack, unequal pair offset each other. (this method assumes that the 
        majority num exists.)
        @Runtime: 60 ms

        :type nums: List[int]
        :rtype: int
        """
        num_stack = []
        for num in nums:
            if not num_stack:
                num_stack.append(num)
            else:
                num_stack.append(num) if num == num_stack[-1] else num_stack.pop()

        return num_stack[-1]

    def majority_element_by_sort(self, nums):
        """
        sort the list and get the middle num.
        @Runtime: 52 ms

        :param nums:
        :return:
        """
        sorted_nums = sorted(nums)
        return sorted_nums[len(nums)//2]

    def majority_element_by_offset(self, nums):
        """
        unequal num offset each other.
        @Runtime: 52 ms

        :param nums:
        :return:
        """
        majority_num = nums[0]
        count = 1

        for x in nums[1:]:
            if count == 0:
                majority_num = x
                count = 1
            elif x == majority_num:
                count += 1
            else:
                count -= 1

        return majority_num

    ## not sulutions
    def majority_element_by_count(self, nums):
        """
        use list.count() method.
        **Time Limit Exceeded**

        :param nums:
        :return:
        """
        majority_count = len(nums) // 2

        for num in nums:
            if nums.count(num) > majority_count:
                return num

        # not found
        return None

if __name__ == '__main__':
    solution = Solution()

    assert solution.majority_element_by_dict([1, 2, 3, 1, 2, 1, 1]) == 1
    assert solution.majority_element_by_dict([1, 1, 1]) == 1
    assert solution.majority_element_by_dict([1]) == 1

    assert solution.majority_element_by_stack([1, 2, 3, 1, 2, 1, 1]) == 1
    assert solution.majority_element_by_stack([1, 1, 1]) == 1
    assert solution.majority_element_by_stack([1]) == 1

    assert solution.majority_element_by_count([1, 2, 3, 1, 2, 1, 1]) == 1
    assert solution.majority_element_by_count([1, 1, 1]) == 1
    assert solution.majority_element_by_count([1]) == 1

    assert solution.majority_element_by_offset([1, 2, 3, 1, 2, 1, 1]) == 1
    assert solution.majority_element_by_offset([1, 1, 1]) == 1
    assert solution.majority_element_by_offset([1]) == 1

    assert solution.majority_element_by_sort([1, 2, 3, 1, 2, 1, 1]) == 1
    assert solution.majority_element_by_sort([1, 1, 1]) == 1
    assert solution.majority_element_by_sort([1]) == 1
