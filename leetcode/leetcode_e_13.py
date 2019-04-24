"""
@link:

    [13. Roman to Integer](https://leetcode.com/problems/roman-to-integer/)

@desc:

    Given a roman numeral, convert it to an integer.

    Input is guaranteed to be within the range from 1 to 3999.

"""

class Solution(object):
    def roman_to_int_by_stack(self, s):
        """
        1. compare adjacent two numerals and determine the operator(+/-) between them;
        2. arrange all numerals and operators in a stack.
        Runtime: 220 ms
        @Reference:
            http://literacy.kent.edu/Minigrants/Cinci/romanchart.htm
            http://www.factmonster.com/ipka/A0769547.html
            
        :type s: str
        :rtype: int
        """
        if not str:
            return 0

        r2i_dict = {'I': 1, 'V': 5, 'X': 10, 'L': 50, 'C': 100, 'D': 500, 'M': 1000}
        num_stack = []
        for i in range(len(s)):
            num = r2i_dict[s[i]]
            if not num_stack:
                num_stack.append(num)
            else:
                if num > num_stack[-1]:
                    num_stack.append('-')
                else:
                    num_stack.append('+')
                num_stack.append(num)

        first_operand = num_stack.pop()
        while num_stack:
            operator = num_stack.pop()
            second_operand = num_stack.pop()
            if operator == '+':
                first_operand += second_operand
            else:
                first_operand -= second_operand

        return first_operand

    def roman_to_int_by_traverse(self, s):
        """
        traverse list reversely, compare adjacent element.
        @Runtime: 172 ms

        :param s:
        :return:
        """
        if not s:
            return 0

        r2i_dict = {'I': 1, 'V': 5, 'X': 10, 'L': 50, 'C': 100, 'D': 500, 'M': 1000}
        index = len(s) - 1
        num = r2i_dict[s[index]]
        while index >= 0:
            first_operand = r2i_dict[s[index]]
            if index - 1 >= 0:
                second_operand = r2i_dict[s[index - 1]]
                if first_operand > second_operand:
                    num -= second_operand
                else:
                    num += second_operand

            index -= 1
        return num

if __name__ == '__main__':
    solution = Solution()
    assert solution.roman_to_int_by_stack("XXXIV") == 34
    assert solution.roman_to_int_by_stack("XXXV") == 35
    assert solution.roman_to_int_by_stack("XCV") == 95
    assert solution.roman_to_int_by_stack("XCVIII") == 98
    assert solution.roman_to_int_by_stack("DCCVII") == 707
    assert solution.roman_to_int_by_stack("MDCCC") == 1800
    assert solution.roman_to_int_by_stack("XVIII") == 18
    assert solution.roman_to_int_by_stack("XLIX") == 49

    #
    assert solution.roman_to_int_by_traverse("XXXIV") == 34
    assert solution.roman_to_int_by_traverse("XXXV") == 35
    assert solution.roman_to_int_by_traverse("XCV") == 95
    assert solution.roman_to_int_by_traverse("XCVIII") == 98
    assert solution.roman_to_int_by_traverse("DCCVII") == 707
    assert solution.roman_to_int_by_traverse("MDCCC") == 1800
    assert solution.roman_to_int_by_traverse("XVIII") == 18
    assert solution.roman_to_int_by_traverse("XLIX") == 49


