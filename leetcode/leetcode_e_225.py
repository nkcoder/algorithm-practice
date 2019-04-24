"""
@link:

    [225. Implement Stack using Queues](https://leetcode.com/problems/implement-stack-using-queues/)

@desc:

    Implement the following operations of a stack using queues.

        push(x) -- Push element x onto stack.
        pop() -- Removes the element on top of the stack.
        top() -- Get the top element.
        empty() -- Return whether the stack is empty.
    Notes:
        You must use only standard operations of a queue -- which means only push to back, peek/pop from front, size, and is empty operations are valid.
        Depending on your language, queue may not be supported natively. You may simulate a queue by using a list or deque (double-ended queue), as long as you use only standard operations of a queue.
        You may assume that all operations are valid (for example, no pop or top operations will be called on an empty stack).

"""


class StackByTwoQueues(object):
    def __init__(self):
        """
        use two queues to implement stack
        operations of queue as a list:
            - push: append(x)
            - pop: pop(0)
            - peek: [0]
            - size: len()
            - empty: not
        @Runtime: 40 ms
        @Reference: https://leetcode.com/discuss/46975/a-simple-c-solution
        initialize your data structure here.
        """
        self.queue1, self.queue2 = [], []

    def push(self, x):
        """
        :type x: int
        :rtype: nothing
        """
        if self.queue1:
            self.queue1.append(x)
        else:
            self.queue2.append(x)

    def pop(self):
        """
        :rtype: nothing
        """
        if self.queue1:
            while len(self.queue1) > 1:
                self.queue2.append(self.queue1.pop(0))
            return self.queue1.pop(0)
        else:
            while len(self.queue2) > 1:
                self.queue1.append(self.queue2.pop(0))
            return self.queue2.pop(0)

    def top(self):
        """
        :rtype: int
        """
        x = None
        if self.queue1:
            while len(self.queue1) > 1:
                self.queue2.append(self.queue1.pop(0))
            x = self.queue1.pop(0)
            self.queue2.append(x)
        else:
            while len(self.queue2) > 1:
                self.queue1.append(self.queue2.pop(0))
            x = self.queue2.pop(0)
            self.queue1.append(x)
        return x

    def empty(self):
        """
        :rtype: bool
        """
        return (not self.queue1) and (not self.queue2)


class StackByOneQueue(object):
    def __init__(self):
        """
        use one queue to implement stack
        operations of queue as a list:
            - push: append(x)
            - pop: pop(0)
            - peek: [0]
            - size: len()
            - empty: not
        @Runtime: 40 ms
        initialize your data structure here.
        """
        self.queue = []

    def push(self, x):
        """
        :type x: int
        :rtype: nothing
        """
        self.queue.append(x)
        queue_size = len(self.queue)
        i = 0
        while i < queue_size - 1:
            self.queue.append(self.queue.pop(0))
            i += 1

    def pop(self):
        """
        :rtype: nothing
        """
        return self.queue.pop(0)

    def top(self):
        """
        :rtype: int
        """
        return self.queue[0]

    def empty(self):
        """
        :rtype: bool
        """
        return not self.queue

if __name__ == '__main__':
    # stack = StackByTwoQueues()
    stack = StackByOneQueue()
    stack.push(1)
    stack.push(2)
    stack.push(3)
    stack.push(4)
    stack.push(5)

    assert stack.pop() == 5
    assert stack.top() == 4
    stack.push(6)
    stack.push(7)
    assert stack.top() == 7
    assert stack.pop() == 7
    assert stack.empty() == False
