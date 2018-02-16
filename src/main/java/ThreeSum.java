package Levski;

import java.util.*;

class ThreeSum {

  // O(n*n) complexity
  public List<List<Integer>> threeSumOne(int[] nums) {
    List<List<Integer>> result = new ArrayList<List<Integer>>();

    if (nums.length < 3 || nums == null) return result;
    Arrays.sort(nums);

    for (int i = 0; i < nums.length - 2; i++) {
      if(i>0 && nums[i] == nums[i-1]){
        continue;
      }
      int left = i+1;
      int right = nums.length - 1;

      while (left < right){
        if((nums[i] + nums[left] + nums[right]) == 0){
          result.add(Arrays.asList(nums[i], nums[left], nums[right]));
          left++;
          right--;
          while(nums[left] == nums[left - 1] && left < nums.length - 2) left++;
          while(nums[right] == nums[right + 1] && right > 1) right--;
        } else if ((nums[i] + nums[left] + nums[right]) > 0) {
          right--;
        } else {
          left++;
        }
      }

    }
    return result;
  }
}
