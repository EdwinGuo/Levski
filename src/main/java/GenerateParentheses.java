package Levski;

import java.util.*;

class GenerateParentheses{

  // Observing the pattern that the first and last must be ( and )
  //   (
  // (             )
  // _ _ _ _ _ _ _ _


  public static void generate(int numLeft, String current, List<String> result, int size) {
    if (numLeft == size) {
      while (current.length() != size * 2){
        current += ")";
      }
      result.add(current);
      return;
    }

    for (int i = 0; i < numLeft + 1; i++){
      String temp = current + new String(new char[i]).replace("\0", ")") ;
      int right = temp.length() - numLeft;
      if (right > numLeft || temp.length() > size*2 - 2) {
        break;
      }
      System.out.println("temp: " + temp + "(");
      generate(numLeft + 1, temp + "(", result, size);
    }
    return;
  }

  public static List<String> generateParenthese(int n) {
    ArrayList<String> result = new ArrayList();
    if (n == 0) return result;

    // the first position will be always (;
    // keep track of how many left parenthese are there.
    int numLeftPare = 1;

    generate(numLeftPare, "(", result, n);

    return result;
  }


  public static void main(String args[]) {
    int n = 4;

    List<String> w = generateParenthese(n);

    System.out.println("Sum of x+y = " + Arrays.toString(w.toArray()));
  }

}
