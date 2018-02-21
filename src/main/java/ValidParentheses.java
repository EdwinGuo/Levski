package Levski;

import java.util.*;
class ValidParentheses {

  //Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

  //The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.

  public static boolean isPair(char l, char r){
    if (l == ')') return '(' == r;
    if (l == ']') return '[' == r;
    if (l == '}') return '{' == r;
  }

  public boolean isValid(String s){
    if (s == "" || s == null) return true;

    Stack stack = new Stack();
    Set left = new HashSet();
    left.add('(');
    left.add('{');
    left.add('[');

    for (int i =0; i < s.length(); i++){
      if (left.contains(s.charAt(i))){
        stack.push(s.charAt(i));
      }
      else {
        if (stack.empty()){
          return false;
        } else {
          if (!isPair(s.charAt(i), stack.pop()))
            return false;
        }
      }
    }
    return stack.empty();
  }

}
