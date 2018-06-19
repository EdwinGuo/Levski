class Solution {
    
    public int integerReplacement(int n) {
        // 11 -> 10 -> 5 -> 4 -> 2 -> 1 
        // 11 -> 12 -> 6 -> 3 -> 2 -> 1
        // 31 -> 30 -> 15 -> 14 -> 7 -> 6 -> 3 -> 2 -> 1   ====> 9
        // 31 -> 32 -> 16 -> 8 -> 4 -> 2 -> 1 ====> 7
        
        
        // 27 -> 26 -> 13 -> 12 -> 6 -> 3 -> 2 -> 1
        // 27 -> 28 -> 14 -> 7 -> 6 -> 3 -> 2 -> 1
        // 27 -> 28 -> 14 -> 8 -> 4 -> 2 -> 1
        if (n == 1) return 0;            
        
        if (n % 2 == 0){
            return integerReplacement(n / 2) + 1;
        } else {
            return Math.min(integerReplacement(n / 2 + 1), integerReplacement(n / 2)) + 2;
        }
    
    }
}
