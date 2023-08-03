package algo.stackQueue;

import java.util.EmptyStackException;

/// 用数组来实现栈
public class ArrayStack {
    /**
     * 栈顶指针,-1代表空栈
     */
    private int top = -1;

    /**
     * 容量大小默认为10
     */
    private int capacity = 10;

    /**
     * 存放元素的数组
     */
    private int[] array;

    /*栈被使用的大小*/
    private int size;


    public ArrayStack() {
        array = new int[capacity];
    }

    /**
     * 栈是否为空
     */
    boolean isEmpty() {
        return size == 0;
    }

    int size() {
        return size;
    }

    /**
     * data元素入栈
     */
    void push(int data) {
        if (array.length == size) {
            ensureCapacity(size * 2);
        }
        array[++top] = data;
        size++;
    }

    /**
     * 出栈,返回栈顶元素,同时从栈中移除该元素
     */
    int pop() {
        if (top == -1) {
            throw new EmptyStackException();
        }
        size--;
        return array[top--];
    }

    /**
     * 返回栈顶元素,未出栈
     */
    int peek() {
        if (top <= -1) {
            throw new EmptyStackException();
        }
        return array[top];
    }

    // 扩容数组
    public void ensureCapacity(int capacity) {
        int[] newArr = new int[capacity];
        System.arraycopy(array, 0, newArr, 0, array.length);
        this.array = newArr;
    }

    public static void main(String[] args) {
        ArrayStack s=new ArrayStack();
        s.push(1);
        s.push(2);
        s.push(3);
        System.out.println("size->"+s.size());
        System.out.println("s.pop->"+s.pop());
        System.out.println("s.pop->"+s.pop());
        s.push(4);
        System.out.println("s.pop->"+s.pop());
        System.out.println("s.isEmpty->"+s.isEmpty());
        System.out.println("s.peek->"+s.peek());
        System.out.println("s.pop->"+s.pop());
        System.out.println("s.isEmpty->"+s.isEmpty());
    }
}


