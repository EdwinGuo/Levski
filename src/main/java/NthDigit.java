class Solution {
    public int findNthDigit(int n) {
        int level = 1;
        long base = 9;
        int start = 1;
        
        while(n > level * base){
            n -= level * base;
            level++;
            base *= 10;
            start *= 10;
        }
        
        start += (n - 1) / level;
        
        char r = Integer.toString(start).charAt((n - 1) % level);
        return Character.getNumericValue(r);
    }      
}
