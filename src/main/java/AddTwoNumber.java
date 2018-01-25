package Levski;

import java.util.*;

// execute as sbt "run-main Levski.AddTwonumber"
class AddTwoNumber {
  // Requirement: Given input:
  // (2, 4, 3) and (5 , 6, 4), looking for (7, 0, 8)
  int[] arr1;
  int[] arr2;

  public AddTwoNumber(int[] a1, int[] a2) {
    arr1 = a1;
    arr2 = a2;
  }

  public LinkedList<Integer> returnResult() {
    int carryOver = 0;
    LinkedList<Integer> result = new LinkedList<Integer>();

    for(int i = 0; i< arr1.length; i++) {
      int intermedia = arr1[i] + arr2[i] + carryOver;
      if (intermedia > 10) {
        // append the number - 10 to result;
        result.add(intermedia - 10);
        // recover the state for carryOver;
        carryOver = 1;
      } else {
        result.add(intermedia);
        carryOver = 0;
      }
    }

    // last step, make sure that the final position is not carry over anything
    if (carryOver > 0) {
      result.add(carryOver);
    }

    return result;
  }

  public static void main(String[] args) {
    int[] a1 = new int[]{3, 5, 2, 9};
    int[] a2 = new int[]{5, 7, 1, 5};

    int[] b1 = new int[]{3, 5, 2, 1};
    int[] b2 = new int[]{5, 7, 1, 5};

    AddTwoNumber atn = new AddTwoNumber(a1, a2);
    AddTwoNumber atn2 = new AddTwoNumber(b1, b2);

    LinkedList<Integer> ll = atn.returnResult();
    LinkedList<Integer> ll2 = atn2.returnResult();


    for (int a: ll) {
      System.out.println("a: " + Integer.toString(a));
    }

    for (int b: ll2) {
      System.out.println("b: " + Integer.toString(b));
    }

  }

}
