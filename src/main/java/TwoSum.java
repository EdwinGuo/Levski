package Levski;

import java.util.*;

// execute as sbt "run-main Levski.TwoSum"
class TwoSum {
  int[] data;
  int target;

  public TwoSum(int[] seed, int v){
    // sort the array first
    Arrays.sort(seed);
    data = seed;
    target = v;
  }

  // n to the power 2
  public LinkedList<Range> returnTwoSumSolutionOne() {
    // A naive solution
    LinkedList<Range> result = new LinkedList<Range>();
    for (int i=0; i< data.length; i++){
      for (int j = i+1; j < data.length; j++) {
        if ((data[i] + data[j]) == target){
          result.add(new Range(data[i], data[j]));
        } else if ((data[i] + data[j]) < target) {
          continue;
        } else {
          break;
        }
      }
    }
    return result;
  }

  // better solution, n*log(n)
  public LinkedList<Range> returnTwoSumSolutionTwo() {
    LinkedList<Range> result = new LinkedList<Range>();

    int[] pointers = new int[2];
    pointers[0] = 0;
    pointers[1] = data.length - 1;

    while(pointers[0] < pointers[1]) {
      if ((data[pointers[0]] + data[pointers[1]]) > target) {
        // If the sum is greater then the target, then move the right to the left for 1 position
        pointers[1] -= 1;
      } else if ((data[pointers[0]] + data[pointers[1]]) < target) {
        // If the sum is less then the target, then move the right to the left for 1 position
        pointers[0] += 1;
      } else {
        result.add(new Range(data[pointers[0]], data[pointers[1]]));
        // we have a hit, move both pointers.
        pointers[1] -= 1;
        pointers[0] += 1;
      }
    }
    return result;
  }

  class Range {
    int left;
    int right;

    public Range(int l, int r) {
      left = l;
      right = r;
    }

    public void setLeft(int l){
      left = l;
    }

    public void setRight(int r){
      right = r;
    }

    public String toString() {
      return ("left: " + Integer.toString(left) + ", right: " + Integer.toString(right));
    }
  }

  public static void main(String[] args) {
    int[] test = new int[]{3, 10, 2, 9, 6, 4, 7};
    int target = 13;
    TwoSum ts = new TwoSum(test, target);

    LinkedList<Range> ll1 = ts.returnTwoSumSolutionOne();
    LinkedList<Range> ll2 = ts.returnTwoSumSolutionTwo();

    for(Range l: ll1) {
      System.out.println("solution 1..:");
      System.out.println(l.toString());
    }

    for(Range l: ll2) {
      System.out.println("solution 2..:");
      System.out.println(l.toString());
    }

  }

}
