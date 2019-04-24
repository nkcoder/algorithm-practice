"""
@link:

    [263. Ugly Number](https://leetcode.com/problems/ugly-number/)

@desc:

    Write a program to check whether a given number is an ugly number.

    Ugly numbers are positive numbers whose prime factors only include 2, 3, 5. For example, 6, 8 are ugly while 14 is not ugly since it includes another prime factor 7.

    Note that 1 is typically treated as an ugly number.

"""


class Solution(object):
    def is_ugly(self, num):
        """
        modulo and divide by 2, 3, 5
        @Runtime: 72 ms

        :type num: int
        :rtype: bool
        """
        if num <= 0:
            return False

        while num % 2 == 0:
            num >>= 1
        while num % 3 == 0:
            num //= 3
        while num % 5 == 0:
            num //= 5

        return num == 1


if __name__ == "__main__":
    solution = Solution()
    assert solution.is_ugly(6) == True
    assert solution.is_ugly(8) == True
    assert solution.is_ugly(14) == False
    assert solution.is_ugly(17) == False
    assert solution.is_ugly(18) == True

