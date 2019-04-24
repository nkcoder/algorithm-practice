"""
@link:

    [326. Power of Three](https://leetcode.com/problems/power-of-three/)

@desc:

    Given an integer, write a function to determine if it is a power of three.

    Follow up:
    Could you do it without using any loop / recursion?

"""


class Solution(object):
    def is_power_of_three_by_loop(self, n):
        """
        modulo and divide by loop
        @Runtime: 452 ms

        :type n: int
        :rtype: bool
        """
        if n <= 0:
            return False

        while n != 1 and n % 3 == 0:
            n //= 3
        return n == 1

    def is_power_of_three_by_recursion(self, n):
        """
        use recursion
        @Runtime: 472 ms
        :param n:
        :return:
        """
        if n <= 0:
            return False

        if n in (1, 3):
            return True
        return n % 3 == 0 and self.is_power_of_three_by_recursion(n // 3)

    def is_power_of_three_by_max(self, n):
        """
        get the max power of three
        @Runtime: 440 ms
        @Reference: https://leetcode.com/discuss/78532/summary-all-solutions-new-method-included-at-15-30pm-jan-8th

        :param n:
        :return:
        """
        max_power_of_three = 1162261467
        return n > 0 and max_power_of_three % n == 0

if __name__ == "__main__":
    solution = Solution()
    assert solution.is_power_of_three_by_loop(3) == True
    assert solution.is_power_of_three_by_loop(9) == True
    assert solution.is_power_of_three_by_loop(27) == True
    assert solution.is_power_of_three_by_loop(15) == False
    assert solution.is_power_of_three_by_loop(81) == True
    assert solution.is_power_of_three_by_loop(243) == True
    assert solution.is_power_of_three_by_loop(-3) == False
    assert solution.is_power_of_three_by_loop(45) == False

    #
    assert solution.is_power_of_three_by_recursion(3) == True
    assert solution.is_power_of_three_by_recursion(9) == True
    assert solution.is_power_of_three_by_recursion(27) == True
    assert solution.is_power_of_three_by_recursion(15) == False
    assert solution.is_power_of_three_by_recursion(81) == True
    assert solution.is_power_of_three_by_recursion(243) == True
    assert solution.is_power_of_three_by_recursion(-3) == False
    assert solution.is_power_of_three_by_recursion(45) == False
