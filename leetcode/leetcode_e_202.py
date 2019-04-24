"""
@link:

    [202. Happy Number](https://leetcode.com/problems/happy-number/)

@desc:

    Write an algorithm to determine if a number is "happy".

    A happy number is a number defined by the following process: Starting with any positive integer, replace the number by the sum of the squares of its digits, and repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1. Those numbers for which this process ends in 1 are happy numbers.

    Example: 19 is a happy number

    1^2 + 9^2 = 82
    8^2 + 2^2 = 68
    6^2 + 8^2 = 100
    1^2 + 0^2 + 0^2 = 1
"""


class Solution(object):
    def is_happy_by_stack(self, n):
        """
        use a stack to store all calculated num.
        @Runtime: 48 ms

        :type n: int
        :rtype: bool
        """
        num_stack = []
        while n != 1:
            square_num = self.cal_square_sum(n)
            if square_num in num_stack:
                return False
            num_stack.append(square_num)
            n = square_num

        return True

    def cal_square_sum(self, num):
        """
        calculate the sum of digit square
        :param num:
        :return:
        """
        square_sum = 0
        while num != 0:
            square_sum += (num % 10)**2
            num //= 10

        return square_sum

    def is_happy_by_variable(self, n):
        """
        use two copy of n, and calculate in different speed, if loop occures, they'll be equal in the end.
        @Runtime: 60 ms
        @Reference: https://leetcode.com/discuss/33349/o-1-space-java-solution

        :param n:
        :return:
        """
        num_slow, num_fast = n, n
        while num_slow != 1:
            num_slow = self.cal_square_sum(num_slow)
            num_fast = self.cal_square_sum(self.cal_square_sum(num_fast))
            if num_slow == 1 or num_fast == 1:
                return True
            if num_slow == num_fast:
                return False

        return True

if __name__ == "__main__":

    solution = Solution()

    assert solution.is_happy_by_stack(1) == True
    assert solution.is_happy_by_stack(19) == True
    assert solution.is_happy_by_stack(5) == False

    assert solution.is_happy_by_variable(1) == True
    assert solution.is_happy_by_variable(19) == True
    assert solution.is_happy_by_variable(5) == False