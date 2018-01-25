package Levski;

import java.util.*;
import java.lang.StringBuilder;

class LongestSubString {
  // given a string, find the longest substring
  String data;

  public LongestSubString(String d) {
    data = d;
  }

  public String returnResult() {
    String result = "";
    StringBuilder current = new StringBuilder();

    for (int i = 0; i < data.length(); i++) {
      char d = data.charAt(i);
      int idx = current.toString().indexOf(d);
      if (idx == -1){
        // continue
        current.append(d);
      } else {

        if (current.length() > result.length()){
          result = current.toString();
        }

        current = new StringBuilder();
        current.append(d);
      }

    }

    return result;
  }

  public static void main(String[] args) {
    String a = "abcabcbb";
    String b = "bbbbb";
    String c = "pwwkew";

    LongestSubString l1 = new LongestSubString(a);
    LongestSubString l2 = new LongestSubString(b);
    LongestSubString l3 = new LongestSubString(c);

    System.out.println("I have " + a + " : " + l1.returnResult());
    System.out.println("I have " + b + " : " + l2.returnResult());
    System.out.println("I have " + c + " : " + l3.returnResult());

  }

}
