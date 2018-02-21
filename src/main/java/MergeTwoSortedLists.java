package Levski;

import java.util.*;

// Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists.
//   Example:
//   Input: 1->2->4, 1->3->4
//   Output: 1->1->2->3->4->4

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class MergeTwoSortedLists {

  public static void merge(ListNode h, ListNode l1, ListNode l2){
    if (l1 == null) {
      h.next = l2;
      return;
    }
    if (l2 == null) {
      h.next = l1;
      return;
    }

    if (l1.val > l2.val) {
      h.next = l2;
      h = h.next;

      l2 = l2.next;
    } else {
      h.next = l1;
      h = h.next;

      l1 = l1.next;
    }

    merge(h, l1, l2);
  }

  public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    if (l1 == null) return l2;
    if (l2 == null) return l1;
    ListNode head = null;

    if (l1.val > l2.val) {
      head = l2;
      l2 = l2.next;
    } else {
      head = l1;
      l1 = l1.next;
    }

    merge(head, l1, l2);

    return head;
  }

  public class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
  }

}
