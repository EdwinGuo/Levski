import java.util.*;

class ReverseSubList {

  public Node reverse(Node lst, int start, int end) {
    // Make sure the list is not empty or null. Assume returning the original list
    // when above condition match
    // Assume start and end are within the range of the length of the Linkedlist
    if (lst.isEmpty() || lst == null || start == end) {
      return lst;
    }

    Node head = lst;
    // to keep track of where the cusor had been moved to.
    Node current = lst;
    // anchor is the position that before the start.
    Node anchor = null;
    // now traverse to the position of start, and start the swap process.
    for (int i = 1; i < end; i++){
      if (i == start){
        if (i == 1){
          // if the start point is the head, then we start a new anchor
          anchor = new Node(1);
        } else {
          anchor = current;
        }
      }

      if (i < start){
        // move the current position
        Node temp = current.next;
        current = temp;
        continue;
      }
      // Now do the swap
      // 1 -> 3 -> 5 -> 10 -> 15 -> 20
      //    anchor |           |
      //Node t = current;
      Node currentNext = current.next;
      anchor.next = currentNext;
      current.next = currentNext.next;
      currentNext.next = current;
    }

    if (start == 1){
      return anchor.next;
    } else {
      return head;}
  }




  class Node {
    int value;
    Node next = null;

    public Node(int v) {
      value = v;
    }

    public boolean isEmpty() {
      return next == null;
    }
  }

}
