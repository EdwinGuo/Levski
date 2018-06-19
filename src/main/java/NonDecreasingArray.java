class Solution {
    public boolean checkPossibility(int[] nums) {
        // check if the input is empty or null
        if (nums.length == 0 || nums == null) return false;
        
        // keep track of how many time the modify action had been issued
        int counter = 0;
        int highestWaterMark = 0;
        int len = nums.length;
        
        for(int i = 0; i < (len - 1); i++){
            if (nums[i] <= nums[i+1]){
                // update water mark;
                highestWaterMark = nums[i];
                continue;
            } 
            else{
                if (counter ==1) return false;
                if (nums[i + 1] >= highestWaterMark){
                  nums[i] = nums[i + 1];
                } else {
                    nums[i + 1] = nums[i];
                }
                counter++;
            }
        }
        return true;
    }
    
    
}
