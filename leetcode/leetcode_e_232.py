"""
@link:

    [232. Implement Queue using Stacks](https://leetcode.com/problems/implement-queue-using-stacks/)

@desc:

    Implement the following operations of a queue using stacks.

        push(x) -- Push element x to the back of queue.
        pop() -- Removes the element from in front of queue.
        peek() -- Get the front element.
        empty() -- Return whether the queue is empty.

    Notes:
        You must use only standard operations of a stack -- which means only push to top, peek/pop from top, size, and is empty operations are valid.
        Depending on your language, stack may not be supported natively. You may simulate a stack by using a list or deque (double-ended queue), as long as you use only standard operations of a stack.
        You may assume that all operations are valid (for example, no pop or peek operations will be called on an empty queue).
"""

class QueueByTwoStacks(object):
    """
    use two stack to implement queue
    operations of stack by list:
        - push: append()
        - pop: pop()
        - peek: [-1]
        - size: len()
        - empty: not
    @Runtime: 44 ms
    """
    def __init__(self):
        """
        initialize your data structure here.
        push_stack: for push
        pop_stack: for pop and peek
        """
        self.push_stack = []
        self.pop_stack = []

    def push(self, x):
        """
        :type x: int
        :rtype: nothing
        """
        self.push_stack.append(x)

    def pop(self):
        """
        move elements from push_stack to pop_stack, then move back
        :rtype: nothing
        """
        while self.push_stack:
            self.pop_stack.append(self.push_stack.pop())
        x = None
        if self.pop_stack:
            x = self.pop_stack.pop()
        while self.pop_stack:
            self.push_stack.append(self.pop_stack.pop())
        return x

    def peek(self):
        """
        :rtype: int
        """
        if self.push_stack:
            return self.push_stack[0]
        return None

    def empty(self):
        """
        :rtype: bool
        """
        return len(self.push_stack) == 0


class QueueByTwoStacks2(object):
    """
    use two stacks to implement queue, maybe a better solution
    @Runtime: 40 ms
    @Reference: https://leetcode.com/discuss/62014/share-my-python-solution-32ms
    """
    def __init__(self):
        """
        initialize your data structure here.
        push_stack: for push
        pop_stack: for pop and peek
        """
        self.push_stack, self.pop_stack = [], []

    def push(self, x):
        """
        :type x: int
        :rtype: nothing
        """
        self.push_stack.append(x)

    def pop(self):
        """
        no need to move back from pop_stack to push_stack
        :rtype: nothing
        """
        if not self.pop_stack:
            while self.push_stack:
                self.pop_stack.append(self.push_stack.pop())
        return self.pop_stack.pop()

    def peek(self):
        """
        :rtype: int
        """
        if not self.pop_stack:
            while self.push_stack:
                self.pop_stack.append(self.push_stack.pop())
        return self.pop_stack[-1]
        # if self.pop_stack:
        #     return self.pop_stack[-1]
        # return self.push_stack[0]

    def empty(self):
        """
        :rtype: bool
        """
        return (not self.push_stack) and (not self.pop_stack)

if __name__ == '__main__':
    # queue = Queue()
    queue = QueueByTwoStacks2()
    queue.push(1)
    queue.push(2)
    queue.push(3)
    queue.push(4)
    queue.push(5)

    assert queue.pop() == 1
    assert queue.pop() == 2
    assert queue.empty() == False
    assert queue.peek() == 3
