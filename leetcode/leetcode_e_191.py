"""
@link:

    [191. Number of 1 Bits](https://leetcode.com/problems/number-of-1-bits/)

@desc:

    Write a function that takes an unsigned integer and returns the number of ’1' bits it has (also known as the Hamming weight).

    For example, the 32-bit integer ’11' has binary representation 00000000000000000000000000001011, so the function should return 3.

"""

class Solution(object):
    def hamming_weight_by_modulo(self, n):
        """
        1. n % 2;
        2. right shift n;
        @Runtime: 68 ms

        :type n: int
        :rtype: int
        """
        num_of_1 = 0
        while n != 0:
            if n % 2 == 1:
                num_of_1 += 1
            n >>= 1

        return num_of_1

    def hamming_weight_by_bit_and(self, n):
        """
        1. n & 1;
        2. left shift 1
        @Runtime: 64 ms

        :param n:
        :return:
        """
        if not n:
            return 0

        one, num_of_1 = 1, 0
        for i in range(32):
            if n & one != 0:
                num_of_1 += 1
            one <<= 1

        return num_of_1

    def hamming_weight_by_minus(self, n):
        """
        1. n & (n -1) to eliminate a `1`
        Runtime: 48 ms

        :param n:
        :return:
        """
        num_of_1 = 0
        while n != 0:
            num_of_1 += 1
            n &= (n - 1)

        return num_of_1


if __name__ == '__main__':
    solution = Solution()
    assert solution.hamming_weight_by_modulo(11) == 3
    assert solution.hamming_weight_by_bit_and(11) == 3
    assert solution.hamming_weight_by_minus(11) == 3

