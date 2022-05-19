package algorithm.stackQueue;

import java.util.Stack;

/// 232. 用栈实现队列 - 官方题解
/// https://leetcode.cn/problems/implement-queue-using-stacks/solution/yong-zhan-shi-xian-dui-lie-by-leetcode-s-xnb6/
public class TwoStackOneQueue {
    Stack<Integer> inStack;
    Stack<Integer> outStack;

    public TwoStackOneQueue() {
        inStack = new Stack<Integer>();
        outStack = new Stack<Integer>();
    }

    public void push(int x) {
        inStack.push(x);
    }

    public int pop() {
        if (outStack.isEmpty()) {
            in2out();
        }
        return outStack.pop();
    }

    public int peek() {
        if (outStack.isEmpty()) {
            in2out();
        }
        return outStack.peek();
    }

    public boolean empty() {
        return inStack.isEmpty() && outStack.isEmpty();
    }

    private void in2out() {
        while (!inStack.isEmpty()) {
            outStack.push(inStack.pop());
        }
    }
}



