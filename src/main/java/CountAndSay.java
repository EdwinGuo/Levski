package Levski;

import java.util.*;

class CountAndSay {

  // The count-and-say sequence is the sequence of integers with the first five terms as following:

  //   1.     1
  //   2.     11
  //   3.     21
  //   4.     1211
  //   5.     111221
  //   1 is read off as "one 1" or 11.
  //   11 is read off as "two 1s" or 21.
  //   21 is read off as "one 2, then one 1" or 1211.
  //   Given an integer n, generate the nth term of the count-and-say sequence.

  //   Note: Each term of the sequence of integers will be represented as a string.

  //   Example 1:

  //   Input: 1
  //   Output: "1"
  //   Example 2:

  //   Input: 4
  //   Output: "1211"


  public String countAndSay(int n) {
    if (n < 0) return "";

    int current = 1;

    String result = "1";

    while(current < n) {
      StringBuilder sb = new StringBuilder();
      int count = 0;
      char currentNum = result.charAt(0);

      for (int i = 0; i <= result.length(); i++) {
        if (i != result.length() && currentNum == result.charAt(i)) {
          count++;
        } else {
          String c =  Integer.toString(count);
          sb.append(c);
          sb.append(currentNum);
          if (i != result.length()){
            count = 1;
            currentNum = result.charAt(i);
          }

        }


      }
      result = sb.toString();
      current++;
    }

    return result;

  }

}
