package Levski;

import java.util.*;

class HIndex {

  // Solution one is the most intuitive approach,=
  // TIme complexity O(n*log(n))
  // Space Complexity O(1)
  public int hIndexOne(int[] citations) {
    if (citations == null || citations.length == 0) return 0;
    Arrays.sort(citations);

    for (int i = 0; i< citations.length; i++){
      if (citations[i] >= citations.length - i) return citations.length - i;
    }

    return 0;
  }

  // Another way to solve the problem, firstly understand what is the math model looks
  // like: Say the length is n, each element in the array can be categoried under either
  // of the two groups. Group 1: less than n, Group 2: greater than n. Think there are
  // n bucket:
  //
  //  |    | |    | |   | |   | |   | |   |
  //  |    | |    | |   | |   | |   | |   |
  //  |____| |____| |___| |___| |___| |___|
  //    1       2     3     4      5     6   ....
  // When you iterate through your array, you make a judgement of which group that element is belong to,
  // if array[i] > n, then bucket[n-1]++ (means you put a ball into that bucket), else
  // you out the ball into the corrensponding bucket (bucket[array[i]] += 1). After you travse
  // all the elements in the array, you iterate through bucket array from the right to left,
  // and at each position, you check whether bucket[i] == i, return if yes otherwise continue.
  // complexicy O(n), space O(n)
  public int hIndexTwo(int[] citations) {
    if (citations == null || citations.length == 0 ) return 0;

    int[] bucket = new int[citations.length];

    int len = citations.length;

    for (int i = 0; i < len; i++){
      if(citations[i] >= len){
        bucket[len - 1] += 1;
      } else if (citations[i] == 0){
        continue;
      } else {
        bucket[citations[i] - 1] += 1;
      }
    }

    int cnt = 0;
    System.out.println("arrays: " + Arrays.toString(bucket));
    for (int j = len - 1; j >= 0; j--){
      if ((bucket[j] + cnt)  >= j + 1){
        return j + 1;
      }
      cnt += bucket[j];
    }

    return 0 ;
  }

  //Follow up for H-Index: What if the citations array is sorted in ascending order? Could you optimize your algorithm?

  public int hIndex(int[] citations) {
    if (citations == null || citations.length == 0 ) return 0;

    int len = citations.length;
    int left = 0;
    int right = len - 1;

    while(left <= right) {
      int pos = left + (right - left) / 2;
      if (citations[pos] == (len - pos)){
        return citations[pos];
      } else if (citations[pos] > (len - pos)) {
        // move left
        right = pos;
      } else {
        // move right
        left = pos + 1;
      }
    }

    System.out.println("Array: " + Arrays.toString(citations) + ", " + Integer.toString(left));
    return citations[left];
  }


}
