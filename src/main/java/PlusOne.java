class Solution {
    public int[] plusOne(int[] digits) {
        if (digits.length ==0 || digits == null) return null;
        
        int[] result;
        int len = digits.length;
        // check if all elements in digits are 9, then claim result size equal to digits.length + 1, else digits.length
        int sumDigits = 0;
        for (int d : digits){
            sumDigits += d;
        }
        
        if (sumDigits == digits.length * 9) {
            result = new int[digits.length + 1];
            
            for (int i =0; i<= result.length -1; i++){
                if (i == 0){
                    result[i] = 1;
                } else {
                    result[i] = 0;
                }
            }
            return result;
        }
        else
            result = new int[digits.length];
        
        int carryOver = 1;
        
        for (int i = digits.length - 1; i>=0; i--){
            int newDigit = digits[i] + carryOver;
            if (newDigit == 10){
                result[i] = 0;
                carryOver = 1;
            } else {
                result[i] = newDigit;
                carryOver = 0;
            }
        }
        
        return result;
    }
}
