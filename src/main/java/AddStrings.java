/*Given two non-negative integers num1 and num2 represented as string, return the sum of num1 and num2.
 *Note:
 *The length of both num1 and num2 is < 5100.
 *Both num1 and num2 contains only digits 0-9.
 *Both num1 and num2 does not contain any leading zero.
 *You must not use any built-in BigInteger library or convert the inputs to integer directly.
*/

class Solution {
    public String addStrings(String num1, String num2) {
        // let num1 always >= num2 in regarding to the length
        if (num1.length() < num2.length()) return addStrings(num2, num1);
        
        int carryOver = 0;
        StringBuilder sb = new StringBuilder();
        
        for(int i = 0; i < num1.length(); i++){
            int num1Index = num1.length() - i - 1;
            int n1 = Character.getNumericValue(num1.charAt(num1Index));
            int n2 = 0;
            if (num2.length() - i -1 >= 0) 
                n2 = Character.getNumericValue(num2.charAt(num2.length() - i -1));
            
            String tempSum = Integer.toString(n1 + n2 + carryOver);
            if (tempSum.length() > 1){
                sb.append(tempSum.charAt(1));
                carryOver = Character.getNumericValue(tempSum.charAt(0));

            } else {
                sb.append(tempSum.charAt(0));
                carryOver = 0;
            }
            
        }
        
        if (carryOver !=0) sb.append(Integer.toString(carryOver));
        
        return sb.reverse().toString();

    }
}
