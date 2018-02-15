package Levski;

import java.util.*;

class ThreeSum {
  public int binarySearch(int[] n, int left, int right, int v) {
    int pointer = left + (right - left) / 2;
    System.out.println("I have left, right" + Integer.toString(left) + ", " + Integer.toString(right) + ", " + Integer.toString(pointer));
    if (left < right - 2){
      if ((n[pointer] + v) == 0) {
        System.out.println("In the list" + Integer.toString(left) + ", " + Integer.toString(right) + ", " + Integer.toString(v));
        return pointer;
      }
      if (v + n[pointer] > 0) {
        return binarySearch(n, left, pointer, v);
      } else {
        return binarySearch(n, pointer + 1, right, v);
      }
    }

    return pointer;
  }

  public List<List<Integer>> threeSum(int[] nums) {
    // O(n*log(n)), n = nums.length();
    List<List<Integer>> result = new ArrayList<List<Integer>>();

    if (nums.length < 3 || nums == null) return result;

    Arrays.sort(nums);
    System.out.println(Arrays.toString(nums));
    int left = 0;
    int right = nums.length - 1;
    while (left < right - 1) {
      int v = nums[left] + nums[right];
      // binary search
      int r = binarySearch(nums, left, right, v);

      System.out.println("xxxxxxxxx");
      System.out.println("sup" + Integer.toString(left) + ", " + Integer.toString(right) + "," + Integer.toString(r) );

      if (v + nums[r] == 0) {
        ArrayList<Integer> res = new ArrayList(3);
        res.add(nums[left]);
        res.add(nums[right]);
        res.add(nums[r]);
        System.out.println("adding.........." + Integer.toString(left) + ", " + Integer.toString(right) + "," + Integer.toString(r) );

        result.add(res);

        left++;
        while(left + 1 < nums.length - 1 && nums[left] == nums[left - 1]) left++;

        right--;
        while(right - 1 > 0 && nums[right] == nums[right + 1]) right--;
      } else if ((v + nums[r]) > 0){
        right--;
      } else {
        left++;
      }
    }
    return result;
  }
}




import java.util.*;
class Solution {
  public List<List<Integer>> threeSum(int[] nums) {
    List<List<Integer>> res = new ArrayList<>();
    Arrays.sort(nums);
    for (int i = 0; i + 2 < nums.length; i++) {
      if (i > 0 && nums[i] == nums[i - 1]) {              // skip same result
        continue;
      }
      int j = i + 1, k = nums.length - 1;
      int target = -nums[i];
      while (j < k) {
        if (nums[j] + nums[k] == target) {
          res.add(Arrays.asList(nums[i], nums[j], nums[k]));
          j++;
          k--;
          while (j < k && nums[j] == nums[j - 1]) j++;  // skip same result
          while (j < k && nums[k] == nums[k + 1]) k--;  // skip same result
        } else if (nums[j] + nums[k] > target) {
          k--;
        } else {
          j++;
        }
      }
    }
    return res;
  }

}
