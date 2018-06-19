class Solution {
    public int repeatedStringMatch(String A, String B) {
        // find the first index and last index of the A from B,
        // and then check whether the curLen = A * (last index - first index) / A.length is in B
        // if yes, then check 0-first index, last index B.length, if both contains then return curLen + 2;
        // else return -1
        if (A.length() == 0 || A == null || B.length() == 0 || B == null || !B.contains(A)) return -1;
        int aLen = A.length();
        int leftIndex = B.indexOf(A);
        int lastIndex = B.lastIndexOf(A);
        int middleOccur = (lastIndex - leftIndex) / aLen + 1;
        StringBuilder sb = new StringBuilder();
        
        for (int i =0; i < middleOccur; i++){
            sb.append(A);
        }
        
        if (!B.contains(sb.toString())) return -1;
        
        int headAndTail = returnHeadAndTail(A, B, leftIndex, lastIndex, aLen);
        return middleOccur + headAndTail;
    }
    
    public int returnHeadAndTail(String A, String B, int leftIndex, int rightIndex, int aLen){
        int result = 0;
        if (leftIndex != 0 && A.contains(B.substring(0, leftIndex))) result++;
        
        if ((rightIndex + aLen) != B.length() && A.contains(B.substring(rightIndex + aLen, B.length()))) result++;       
        return result;
    }
    
}
