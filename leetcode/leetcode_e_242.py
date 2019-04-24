"""
@link: [242. Valid Anagram](https://leetcode.com/problems/valid-anagram/)

@desc:

    Given two strings s and t, write a function to determine if t is an anagram of s.

    For example,
    s = "anagram", t = "nagaram", return true.
    s = "rat", t = "car", return false.

    Note:
    You may assume the string contains only lowercase alphabets.

    Follow up:
    What if the inputs contain unicode characters? How would you adapt your solution to such case?

@reference: https://leetcode.com/discuss/50580/python-solutions-sort-and-dictionary
"""


class Solution(object):
    def is_anagram_by_list(self, s, t):
        """
        use list to record character occurrence.
        of course you can use two list and compare if they are equal.
        @Runtime: 88 ms

        :param s:
        :param t:
        :return:
        """
        # cannot use `if not s or not t`, since when s and t are None, or are empty, the statement
        # always return True.
        if s is None or t is None:
            return False
        if len(s) != len(t):
            return False

        # initialize the list with all zeros
        count = [0] * 26
        # get ASCII number of a character
        ord_a = ord('a')
        index = 0
        while index < len(s):
            count[ord(s[index]) - ord_a] += 1
            count[ord(t[index]) - ord_a] -= 1
            index += 1

        for x in count:
            if x != 0:
                return False

        return True

    def is_anagram_by_dict(self, s, t):
        """
        use dict to record character occurrence.
        of course you can use two dict and compare if they are equal.
        @Runtime: 76 ms

        :param s:
        :param t:
        :return:
        """
        if s is None or t is None:
            return False
        if len(s) != len(t):
            return False

        count_dict = {}

        for x in s:
            count_dict[x] = count_dict.get(x, 0) + 1

        for x in t:
            count_dict[x] = count_dict.get(x, 0) - 1

        for value in count_dict.values():
            if value != 0:
                return False

        return True

    #################################################################

    def is_anagram_by_sort(self, s, t):
        """
        first sort the two string and compare if they are equal
        **IMPORTANT**: Status: Time Limit Exceeded
        :type s: str
        :type t: str
        :rtype: bool
        """
        if s is None or t is None:
            return False
        if len(s) != len(t):
            return False

        # you can use python sorted() function
        return sorted(s) == sorted(t)


if __name__ == '__main__':
    solution = Solution()

    assert solution.is_anagram_by_dict('anagram', 'nagaram') == True
    assert solution.is_anagram_by_dict('rat', 'car') == False
    assert solution.is_anagram_by_dict('aabccd', 'ccaabd') == True
    assert solution.is_anagram_by_dict('', '') == True




