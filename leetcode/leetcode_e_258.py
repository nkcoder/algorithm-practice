"""
@link: [258. Add Digits](https://leetcode.com/problems/add-digits/)

@desc:

    Given a non-negative integer num, repeatedly add all its digits until the result has only one digit.

    For example:

    Given num = 38, the process is like: 3 + 8 = 11, 1 + 1 = 2. Since 2 has only one digit, return it.

    Follow up:
    Could you do it without any loop/recursion in O(1) runtime?
"""


class Solution(object):
    def add_digits_with_loop(self, num):
        """
        hint: use loop
        @Runtime: 64 ms

        :type num: int
        :rtype: int
        """
        while num // 10 != 0:
            sum_of_num = 0
            tmp = num
            while tmp > 0:
                sum_of_num += tmp % 10
                tmp //= 10
            num = sum_of_num
        return num

    def add_digits_without_loop(self, num):
        """
        hint:
            1. What are all the possible results?
            2. How do they occur, periodically or randomly?
            3. You may find this Wikipedia article useful.
        @Runtime: 64 ms

        :type num: int
        :rtype: int
        """
        if num == 0:
            return 0

        remain = num % 9
        return remain if remain != 0 else 9

    def add_digits_without_loop2(self, num):
        """
        hint: use formula: dr(n) = n - 9 * [(n-1)/9] inferred from add_digits_without_loop()
        @Runtime: 56 ms

        :param num:
        :return:
        """
        if num == 0:
            return 0
        return num - 9 * ((num - 1) // 9)

if __name__ == '__main__':
    solution = Solution()
    assert solution.add_digits_with_loop(0) == solution.add_digits_without_loop(0)
    assert solution.add_digits_with_loop(38) == solution.add_digits_without_loop(38)
    assert solution.add_digits_with_loop(531) == solution.add_digits_without_loop(531)
    assert solution.add_digits_with_loop(532) == solution.add_digits_without_loop(532)
    assert solution.add_digits_with_loop(50) == solution.add_digits_without_loop(50)
    assert solution.add_digits_with_loop(15869) == solution.add_digits_without_loop(15869)

    assert solution.add_digits_with_loop(0) == solution.add_digits_without_loop(0)
    assert solution.add_digits_with_loop(38) == solution.add_digits_without_loop(38)
    assert solution.add_digits_with_loop(531) == solution.add_digits_without_loop(531)
    assert solution.add_digits_with_loop(50) == solution.add_digits_without_loop(50)
    assert solution.add_digits_with_loop(15869) == solution.add_digits_without_loop(15869)
