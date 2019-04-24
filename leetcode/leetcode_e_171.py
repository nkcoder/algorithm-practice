"""
@link: [171. Excel Sheet Column Number](https://leetcode.com/problems/excel-sheet-column-number/)

@desc:

    Related to question Excel Sheet Column Title

    Given a column title as appear in an Excel sheet, return its corresponding column number.

    For example:

        A -> 1
        B -> 2
        C -> 3
        ...
        Z -> 26
        AA -> 27
        AB -> 28

@similar: [168. Excel Sheet Column Title](https://leetcode.com/problems/excel-sheet-column-title/)
"""
import string


class Solution(object):
    def title_to_number(self, s):
        """
        @Run Time: 68ms

        :type s: str
        :rtype: int
        """
        if not s:
            return None

        ascii_num_dict = {}
        value = 1
        for c in string.ascii_uppercase:
            ascii_num_dict[c] = value
            value += 1

        index = 0
        threshold = 26
        result = 0
        for c in reversed(s):
            result += ascii_num_dict[c] * (threshold ** index)
            index += 1

        """
        power = 1
        base = 26
        result = 0
        for c in reversed(s):
            result += ascii_num_dict[c] * power
            power *= base
        """

        return result


if __name__ == '__main__':

    solution = Solution()

    assert solution.title_to_number('Z') == 26
    assert solution.title_to_number('') is None
    assert solution.title_to_number('A') == 1
    assert solution.title_to_number('AA') == 27
    assert solution.title_to_number('AZ') == 52
    assert solution.title_to_number('BA') == 53
    assert solution.title_to_number('BZ') == 78





