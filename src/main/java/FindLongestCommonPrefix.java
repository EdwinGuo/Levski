package Levski;

class FindLongestCommonPrefix {
  public String longestCommonPrefix(String[] strs) {
    if (strs.length == 0) return "";
    if (strs.length == 1) return strs[0];

    StringBuilder sb = new StringBuilder();
    int shortest = Integer.MAX_VALUE;
    boolean term = false;
    for (int i = 0; i < strs.length; i++){
      if (strs[i].length() < shortest) {
        shortest = strs[i].length();
      }
    }

    for (int i = 0; i < shortest; i++) {
      char s = strs[0].charAt(i);
      int j;
      for (j = 1; j < strs.length; j++){
        if (strs[j].charAt(i) != s){
          term = true;
          break;
        }
      }
      if (term) {
        break ;
      }
      else {
        sb.append(s);
      };
    }

    return sb.toString();
  }
}
