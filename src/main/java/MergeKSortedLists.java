package Levski;

import java.util.*;

class MergeKSortedLists {
  // complexity n * log(k)
  public void scan(PriorityQueue<ListNode> pq, ListNode current) {
    ListNode node = pq.remove();
    current.next = node;
    if (node.next != null){
      pq.add(node.next);
    }
  }

  public ListNode mergeKLists(ListNode[] lists) {
    if (lists == null || lists.length == 0) return null;

    int k = 0;

    PriorityQueue<ListNode> pq = new PriorityQueue<ListNode>(100, (a, b) -> a.val - b.val);

    for(ListNode list: lists){
      k++;
      if (list != null) pq.add(list);
    }

    ListNode temp = pq.isEmpty() ? null : pq.remove();
    ListNode result = temp;
    if (result != null && result.next != null) pq.add(result.next);
    ListNode current = result;

    while(!pq.isEmpty()){
      scan(pq, current);
      current = current.next;
    }

    return result;
  }
}
