package Levski;

import java.util.*;

class BurstBalloons {
  // hint from: https://www.youtube.com/watch?v=oxPTLTPEckw

  public int calculateRange(int[] nums, int[][] memo, int start, int end) {
    if (start > end) return 0;
    if (memo[start][end] > 0) return memo[start][end];

    for(int i = start; i <= end; i++) {
      //System.out.println("Start" + Integer.toString(start) + ", end: " + Integer.toString(end));
      memo[start][end] = Math.max(memo[start][end], calculateRange(nums, memo, start, i-1) +
                                  nums[start - 1] * nums[i] * nums[end + 1] +
                                  calculateRange(nums, memo, i + 1, end));
    }
    return memo[start][end];
  }

  public int maxCoins(int[] nums) {
    if (nums.length == 1) return nums[0];
    if (nums.length == 0) return 0;
    int[] newNums = new int[nums.length + 2];

    // to Memorize the calculated value
    int[][] memo = new int[newNums.length][newNums.length];
    //System.out.println("Demention" + Integer.toString(memo[0].length) + ", end: " + Integer.toString(memo.length));

    // firstly, assign 1 to both end
    newNums[0] = 1;
    newNums[newNums.length -1] = 1;

    for (int i = 0; i < nums.length; i++) {
      newNums[i+1] = nums[i];
    }

    calculateRange(newNums, memo, 1, nums.length);
    return memo[1][nums.length];
  }

}
