package Levski;

class LongestPalindromicSubstring {
  // worse case senerio of time complexity will be O(n*2*(sum(1 to n/2)))
  // which is every character in the str is the same, like aaaaaaaaa
  // space is O(1)

  // Can protential add additional check and reduce the time complexity by
  // adding more length check with the looping logic

  // public String longestPalindrome(String str) {
  //   // Assume if length equal to 1 then return string itself
  //   if (str == "" || str.length() == 1) return str;
  //   // keep track of the longest palindromic string;
  //   String result = "";

  //   for (int i = 1; i< str.length(); i++){
  //     int left = i -1;
  //     int right = i + 1;
  //     // make sure to catch the case where current pointer will match its neigbour to find the at least 2 element
  //     if (result.length() == 0) {
  //       if (str.charAt(left) == str.charAt(i)){
  //         result = str.substring(left, i);
  //       }

  //       if (str.charAt(right) == str.charAt(i)) {
  //         result = str.substring(i, right);
  //       }
  //     }

  //     while(left >=0 && right <str.length() && str.charAt(left) == str.charAt(right)){
  //       if ((right - left) > result.length()) {
  //         result = str.substring(left, right);
  //       }
  //     }
  //   }
  //   return result;
  // }
  int low = 0;
  int high = 0;

  public void update(String s, int left, int right){
    if (((right - left) > (high - low))) {
      low = left;
      high = right;
    }
  }

  public String longestPalindrome(String str) {
    // Assume if length equal to 1 then return string itself
    if (str == "" || str.length() == 1) return str;

    // We might see (ab|ba)cc
    // or (aba)cc, so even or odd number
    for (int i = 0; i< str.length() - 1; i++){
      if (str.charAt(i) == str.charAt(i+1)){
        update(str, i, i + 1);
        extend(str, i-1, i+2);
      }
      extend(str, i-1, i+1);
    }
    System.out.println("Test: " + str);
    return str.substring(low, high + 1);
  }

  public void extend(String s, int left, int right){
    while(left >=0 && right <s.length() && s.charAt(left) == s.charAt(right)){
      update(s, left, right);
      left--;
      right++;
    }
  }

}
