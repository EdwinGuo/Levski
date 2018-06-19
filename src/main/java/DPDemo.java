import java.util.Arrays;
public class HelloWorld{
    public static int largestDivisibleSubset(int[] nums) {
        // Using a dp solution to solve the problem.
        // In order to save some effort of re-compute,
        // we should sort the array first in an asending order,
        // after that we iterate through the sorted array and then
        // check elements against the left.
        
        //if (nums == null) return null;
        
        //comp: n*logn
        Arrays.sort(nums);
        
        // space n
        int[] dp = new int[nums.length];
        
        dp[0] = 1;
        
        // comp n^2
        for (int i = 1; i < nums.length; i++){
            for (int j = i; j>=0; j--) {
                if (nums[i] % nums[j] == 0) dp[i] = Math.max(dp[j] + 1, dp[i]);
            }
        }
        
        int result = 0;
        
        for (int re: dp){
            System.out.println("dp: " + re);
            if (result < re) result = re;
        }
        
        return result;
        
    }
    
     public static void main(String []args){
        int[] nums = new int[]{2,3,5,6,12};
        int x =  largestDivisibleSubset(nums);
        System.out.println("Hello World, " + x);
     }
}
