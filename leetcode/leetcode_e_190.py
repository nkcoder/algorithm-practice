"""
@link:

    [190. Reverse Bits](https://leetcode.com/problems/reverse-bits/)

@desc:

    Reverse bits of a given 32 bits unsigned integer.

    For example, given input 43261596 (represented in binary as 
    00000010100101000001111010011100), return 964176192 (represented in binary 
    as 00111001011110000010100101000000).

    Follow up:
    If this function is called many times, how would you optimize it?

    Related problem: Reverse Integer

"""

class Solution(object):
    def reverse_bits_by_power(self, n):
        """
        Runtime: 56 ms

        :type n: int
        :rtype: int
        """
        reverse_num = 0
        index = 0
        while n:
            if n & 1:
                reverse_num += 2 ** (31 - index)
            n >>= 1
            index += 1

        return reverse_num

    def reverse_bits_by_bit(self, n):
        """
        Runtime: 52 ms

        :param n:
        :return:
        """
        reverse_num = 0
        for index in range(32):
            reverse_num += (n & 1)
            # first bit, don't left shift
            if index < 31:
                reverse_num <<= 1
            n >>= 1

        return reverse_num


if __name__ == "__main__":
    solution = Solution()
    assert solution.reverse_bits_by_power(43261596) == 964176192
    assert solution.reverse_bits_by_bit(43261596) == 964176192
