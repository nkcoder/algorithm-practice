"""
@link:

    [8. String to Integer (atoi)](https://leetcode.com/problems/string-to-integer-atoi/)

@desc:

    Implement atoi to convert a string to an integer.

    Hint: Carefully consider all possible input cases. If you want a challenge, please do not see below and ask yourself what are the possible input cases.

    Notes: It is intended for this problem to be specified vaguely (ie, no given input specs). You are responsible to gather all the input requirements up front.

"""

class Solution(object):
    def a_to_i(self, str):
        """
        in python 3, int is the same as long, which is much bigger than 2**31:
            >>> sys.maxsize
            9223372036854775807
        @Runtime: 76 ms
        @Reference: https://leetcode.com/discuss/32813/java-solution-with-4-steps-explanations

        :type str: str
        :rtype: int
        """

        if not str:
            return 0

        # 1. leading and trailing spaces
        str = str.strip()
        start_index, sign = 0, 1

        # 2. sign
        if str[0] == '+':
            start_index = 1
        elif str[0] == '-':
            start_index = 1
            sign = -1

        num = 0
        num_max = 2**31 - 1
        num_min = -2**31
        # 3. when non-digit encountered, stop and return
        for (index, c) in enumerate(str[start_index:], start=start_index):
            if ord(c) - ord('0') < 0 or ord(c) - ord('0') > 9:
                break
            num = num * 10 + (ord(c) - ord('0'))

        # 4. overflow
        num *= sign
        num = max(num_min, min(num, num_max))
        return num

if __name__ == '__main__':
    solution = Solution()
    assert solution.a_to_i('') == 0
    assert solution.a_to_i(None) == 0
    assert solution.a_to_i('56.7') == 56

    assert solution.a_to_i('567') == 567
    assert solution.a_to_i('+567') == 567
    assert solution.a_to_i('-567') == -567
    assert solution.a_to_i('+000567') == 567
    assert solution.a_to_i('-000567') == -567
    assert solution.a_to_i('     0567   ') == 567
    assert solution.a_to_i('     -010') == -10
    assert solution.a_to_i("  -0012a42") == -12
    assert solution.a_to_i("2147483648") == 2147483647








