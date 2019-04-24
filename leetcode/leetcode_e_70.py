"""
@link:

    [70. Climbing Stairs](https://leetcode.com/problems/climbing-stairs/)

@desc:

    You are climbing a stair case. It takes n steps to reach to the top.

    Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?

"""


class Solution(object):
    def climb_stairs(self, n):
        """
        f(n) = f(n-1) + f(n-2), use iteration
        @Runtime: 60 ms

        :type n: int
        :rtype: int
        """
        if n in (0, 1, 2):
            return n

        f1 = 1
        f2 = 2
        for i in range(3, n + 1):
            f = f1 + f2
            f1 = f2
            f2 = f

        return f2

if __name__ == "__main__":
    solution = Solution()

    assert solution.climb_stairs(3) == 3
    assert solution.climb_stairs(5) == 8
    assert solution.climb_stairs(6) == 13
    assert solution.climb_stairs(7) == 21



