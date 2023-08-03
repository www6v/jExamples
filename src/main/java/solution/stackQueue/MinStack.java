package solution.stackQueue;

import java.util.Stack;

/// https://leetcode.cn/problems/min-stack/solution/zui-xiao-zhan-by-leetcode-solution/
/// Leetcode 155
/// 方法一：辅助栈
public class MinStack {
    Stack<Integer> xStack;
    Stack<Integer> minStack;

    public MinStack() {
        xStack = new Stack<Integer>();
        minStack = new Stack<Integer>();
        minStack.push(Integer.MAX_VALUE);
    }

    public void push(int x) {
        xStack.push(x);
        minStack.push(Math.min(minStack.peek(), x));
    }

    public void pop() {
        xStack.pop();
        minStack.pop();
    }

    public int top() {
        return xStack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}

