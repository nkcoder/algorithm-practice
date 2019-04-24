"""
@link:

    [231. Power of Two](https://leetcode.com/problems/power-of-two/)

@desc:

    Given an integer, write a function to determine if it is a power of two.

"""
class Solution(object):
    def isPowerOfTwo(self, n):
        """
        @Runtime: 64 ms

        :type n: int
        :rtype: bool
        """
        if not n:
            return False
        return not n & (n - 1)


if __name__ == "__main__":
    solution = Solution()
    assert solution.isPowerOfTwo(2) == True
    assert solution.isPowerOfTwo(3) == False
    assert solution.isPowerOfTwo(4) == True
    assert solution.isPowerOfTwo(8) == True
    assert solution.isPowerOfTwo(0) == False
