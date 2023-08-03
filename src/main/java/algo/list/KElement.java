package algo.list;

public class KElement {
    /// 双指针
    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode fast = head;
        ListNode slow = head;

        /// fast 先走k步伐
        while (fast != null && k > 0) {
            fast = fast.next;
            k--;
        }
        /// fast和slow一起走
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        return slow;
    }

    class ListNode {
        public int value;
        public ListNode next;

        public ListNode(int data) {
            this.value = data;
        }
    }
}


