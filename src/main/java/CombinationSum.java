class Solution {


    // using a recursive function to calculate the distinct number of
   // combination of integerst array which sum is equal to target
    public int combinationSum4(int[] nums, int target) {
        // Assume that each element can use multiple times
        // Assume that the nums had already pre-sorted
        if (nums == null || nums.length == 0 || target < 0) return 0;
        
        // define a result
        int result = recurCall(nums, nums.length - 1, target);
        
        return result;
    }
    
    public int recurCall(int[] nums, int currentIndex, int target){
        System.out.println("target, target: " + target + ", currentIndex " + currentIndex);
        // define the termination condition here
        if (currentIndex < 0) return 0;
        if (target < 0) return 0;
        if (target == 0) return 1;
        
        int result = 0;
        
        result += recurCall(nums, currentIndex, target - nums[currentIndex]);
        result += recurCall(nums, currentIndex - 1, target);
        
        return result;
    }
   

   //=========================== a legit dp solution==========================
    public int combinationSum4(int[] nums, int target) {
        // using dp solution to keep track of historical watermark
        // define the dp array
        int[] dp = new int[target+1];
        dp[0] = 1;
        
        for(int i = 1; i <= target; i++){
            for (int num: nums){
                if ((i - num) >= 0){
                    dp[i] += dp[i-num];
                }
            }
        }
        
        return dp[target];
    }


   //===============================================================================
 
   




 
}
