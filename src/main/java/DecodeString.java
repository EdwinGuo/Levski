import java.util.*;

class Solution {
    
    public String decodeString(String s) {
        // make sure the input doesn't fall within the extreme case
        
        // define the variable here:
        Stack data = new Stack();
        StringBuilder sb = new StringBuilder();
        
        int round = 0;
        
        for (int i = 0; i < s.length(); i++){
            if (s.charAt(i) == ']'){
                massageStack(data, round);
                round++;
            } else {
                data.add(s.charAt(i));
            }
        }
        
        // return the result
        while(!data.empty()){
            sb.append(data.pop());
        }
        
        return sb.reverse().toString();
    }
    
    public static void massageStack(Stack data, int round){
        StringBuilder sbStr = new StringBuilder();
        StringBuilder sbNum = new StringBuilder();
        String stage = "str";
        
        while(!data.empty()) {
            if ((char) data.peek() == '['){
                data.pop();
                stage = "num";
                continue;
            }
            
            if (stage == "str") {
                sbStr.append((char) data.pop());
            } else {
                if (isDigit((char) data.peek())) {
                    sbNum.append((char) data.pop());
                    if (!data.empty() && (char) data.peek() == '['){
                        break;
                    }
                } else {
                    break;
                }
                
            }
        }
        
        System.out.println("sbStr , round  " + round + "," + sbStr.toString());
        System.out.println("sbNum , round  " + round + "," + sbNum.toString());
        round++;
        // now return the result
        String str = sbStr.reverse().toString();
        int num = Integer.parseInt(sbNum.reverse().toString());
        StringBuilder temp = new StringBuilder();
        for (int i = 0; i < num; i++){
            temp.append(str);
        }
        
        
        for (int i = 0; i < temp.toString().length(); i++){
            data.add(temp.charAt(i));
        }
    }
    
    public static boolean isDigit(char c){
        return Character.isDigit(c);
    }
    
}
