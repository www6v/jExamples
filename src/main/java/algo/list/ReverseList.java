package algo.list;

import lombok.Getter;
import lombok.Setter;

/// Leetcode 206
public class ReverseList {

    public static void main(String args[]) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);

        node1.setNext(node2);
        node2.setNext(node3);
        node3.setNext(node4);
        node4.setNext(node5);
    }

    /// self
    public static Node reverseList(Node node) {
        Node pre = null;
        Node next = null;
        while (node != null) {
            next = node.next;
            node.next = pre;
            pre = node;
            node = next;
        }
        return pre;
    }

    /// https://leetcode.cn/problems/reverse-linked-list/solution/shi-pin-jiang-jie-die-dai-he-di-gui-hen-hswxy/
    public static ListNode reverseList(ListNode head) {
         ListNode prev = null;
         ListNode curr = null;
         while(head.next!=null) {
             ListNode next = curr.next; /// 临时节点
             curr.next = prev;
             prev = curr;
             curr = next;
         }
         return prev;
    }


    @Getter
    @Setter
    static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    class ListNode {
        public int value;
        public ListNode next;

        public ListNode(int data) {
            this.value = data;
        }
    }
}

