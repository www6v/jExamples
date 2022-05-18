package algorithm.list;

/// 链表中是否有环， 快慢指针
public class DetectCycle {
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public boolean detectCycle(ListNode head) {
      if(head == null) return false;
      ListNode slow = head;
      ListNode fast = head;
      while(fast.next !=null && fast.next.next !=null) {
          slow = slow.next;
          fast = fast.next.next;
          if(slow == fast) {
              return true;
          }
      }

      return false;
    }
}

