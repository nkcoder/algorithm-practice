"""
@link: [168. Excel Sheet Column Title](https://leetcode.com/problems/excel-sheet-column-title/)

@desc:

    Given a positive integer, return its corresponding column title as appear in an Excel sheet.

    For example:

        1 -> A
        2 -> B
        3 -> C
        ...
        26 -> Z
        27 -> AA
        28 -> AB

@similar: [171. Excel Sheet Column Number](https://leetcode.com/problems/excel-sheet-column-number/)
"""


import string


class Solution(object):
    def convert_to_title(self, n):
        """
        similar to binary representation rules
        @Runtime: 36 ms

        :type n: int
        :rtype: str
        """
        if not n:
            return None

        ascii_num_dict = {}
        value = 1
        for c in string.ascii_uppercase:
            ascii_num_dict[value] = c
            value += 1

        # since % operation could be 0, the modulo should always be subtracted
        result = ''
        threshold = 26
        while n != 0:
            modulo = n % threshold
            num = modulo if modulo != 0 else threshold
            result = ascii_num_dict[num] + result
            n = (n - num) // threshold

        return result

if __name__ == '__main__':
    solution = Solution()

    assert solution.convert_to_title(26) == 'Z'
    assert solution.convert_to_title(0) is None
    assert solution.convert_to_title(1) == 'A'
    assert solution.convert_to_title(27) == 'AA'
    assert solution.convert_to_title(52) == 'AZ'
    assert solution.convert_to_title(53) == 'BA'
    assert solution.convert_to_title(78) == 'BZ'


