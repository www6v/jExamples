package algo.company.dataVisor;

/// 排序链表
public class Leetcode148 {
    class ListNode {
        public int value;
        public ListNode next;

        public ListNode(int data) {
            this.value = data;
        }

        public ListNode(int val, ListNode next) {
            this.value = val;
            this.next = next;
        }
    }

    public ListNode sort(ListNode head) {
        if(head == null) {
            return head;
        }

        int length  = 0;
        ListNode node = head;
        while(node != null){
            length ++;
            node = node.next;
        }
        ListNode dummyHead = new ListNode(0, head);
        for (int subLength = 0; subLength < length; subLength <<= 1) {
            ListNode prev = dummyHead;
            ListNode curr = dummyHead.next;
            while(curr != null) {
                ListNode head1 = curr;
                for (int i = 1; i < subLength && curr.next != null; i++) {
                    curr = curr.next;
                }

                ListNode head2 = curr.next;
                curr.next = null;
                curr = head2;
                for (int i = 0; i < subLength && curr!=null&& curr.next!=null; i++) {
                    curr = curr.next;
                }

                ListNode next = null;
                if(curr != null) {
                    next = curr.next;
                    curr.next =null;
                }

                ListNode merged = merge(head1,head2);
                prev.next = merged;
                while(prev.next!=null) {
                    prev = prev.next;
                }
                curr = next;
            }
        }

        return dummyHead.next;
    }

    public ListNode merge(ListNode head1, ListNode head2) {
        ListNode dummyHead = new ListNode(0);
        ListNode temp = dummyHead;
        ListNode temp1 = head1;
        ListNode temp2 = head2;

        while(temp1 !=null && temp2!=null){
           if(temp1.value <= temp2.value) {
               temp.next = temp1;
               temp1 = temp1.next;
           } else {
               temp.next = temp2;
               temp2 = temp2.next;
           }
            temp = temp.next;
        }

        if(temp1 != null) {
            temp.next = temp1;
        } else if(temp2 !=null) {
            temp.next = temp2;
        }

        return dummyHead.next;
    }
}