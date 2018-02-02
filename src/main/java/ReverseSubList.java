package Levski;

import java.util.*;

class ReverseSubList {

  // execute as sbt "run-main Levski.ReverseSubList"
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
      // Traverse to the start position
      if (i < start){
        // when the next move is the start of the swap, plot anchor
        if ((start - i) == 1) {
          anchor = current;
        }

        // move the current position
        Node temp = current.next;
        current = temp;

        continue;
      }

      if (i == start && i == 1){
        // if the start point is the head, then we start a new anchor
        anchor = new Node(1);
        anchor.next = head;
        swap(anchor, current);
        continue;
      }

      // Now do the swap
      // 1 -> 3 -> 5 -> 10 -> 15 -> 20
      //    anchor |           |
      //Node t = current;
      swap(anchor, current);
      System.out.println("What is my anchor: " + Integer.toString(anchor.value));
    }

    if (start == 1){
      return anchor.next;
    } else {
      return head;}
  }

  public void swap(Node anchor, Node current) {
    Node currentNext = current.next;
    Node anchorNext = anchor.next;
    anchor.next = currentNext;
    current.next = currentNext.next;
    currentNext.next = anchorNext;
  }

  static class Node {
    int value;
    Node next = null;

    public Node(int v) {
      value = v;
    }

    public boolean isEmpty() {
      return next == null;
    }
  }

  public static void main(String[] args) {
    // Prepare the test cases
    // test1: 5 -> 3 -> 7 -> 8 -> 2
    int[] t1 = new int[]{5,3,7,8,2};
    Node test1 = new Node(5);
    Node temp1 = test1;

    // to populate the rest of the chain
    for (int i = 1; i < t1.length; i++) {
      Node newNode = new Node(t1[i]);
      temp1.next = newNode;
      temp1 = newNode;
    }

    ReverseSubList rs1 = new ReverseSubList();
    Node n = rs1.reverse(test1, 1, 4);
    Node temp = n;
    while(!temp.isEmpty()) {
      System.out.println(Integer.toString(temp.value) + ",");
      Node inter = temp.next;
      temp = inter;
    }

    System.out.println(Integer.toString(temp.value) + ",");





  }
}
