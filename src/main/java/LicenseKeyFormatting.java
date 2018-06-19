class Solution {
    public String licenseKeyFormatting(String S, int K) {
        // check the case where the length of the alphanumerical characters is long
        // "2-3g-a" , k = 5, return ""
        // "2-3g-a", k = 4, return ""
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < S.length(); i++) {
            if (S.charAt(i) == '-') continue;
            char temp = S.charAt(i);
            char appendChar = Character.isDigit(temp) ? temp : Character.toUpperCase(temp) ;
            sb.append(appendChar);
        }
        
        String str = sb.toString();
        if (str.length() / K == 0) return str;
        
        int headLen = str.length() % K;
        int numPosition = headLen == 0 ? str.length() / K : str.length() / K + 1;   

        StringBuilder result = new StringBuilder();
        int start = 0;
        
        for(int i = 0; i < numPosition; i++){
            if (i ==0) {
                int idx = headLen == 0 ? K : headLen;
                start += idx;
                String toAppend = numPosition == 1 ? str.substring(0, idx) : str.substring(0, idx) + "-";
         
                result.append(toAppend);
                continue;
            }
            
            String toAppend = i == (numPosition - 1) ? str.substring(start, str.length()) : str.substring(start, start + K) + "-";  
            start += K;

           result.append(toAppend);
        }
        return result.toString();
    }
}
