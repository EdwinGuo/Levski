package Levsk;

class SplitArrayLargestSum {
  // Given an array which consists of non-negative integers and an integer m, you can split the array into m non-empty continuous subarrays. Write an algorithm to minimize the largest sum among these m subarrays.
  //   Note:
  // If n is the length of array, assume the following constraints are satisfied:
  // 1 ≤ n ≤ 1000
  //   1 ≤ m ≤ min(50, n)
  //   Examples:
  // Input:
  // nums = [7,2,5,10,8]
  //   m = 2
  //   Output:
  // 18
  //   Explanation:
  // There are four ways to split nums into two subarrays.
  //   The best way is to split it into [7,2,5] and [10,8],
  //   where the largest sum among the two subarrays is only 18.

  // First thing to consider is that what are you searching for, there are two things that you can
  // think, first is that you search for all the possible combinations and then each iteration you
  // record the biggest one. At the end of each iteration, compare the current biggest with the historical
  // biggest watermark, if over then continue, otherwise, replace the cache. Complexity will be O(n^m-1)

  // Another way to consider this is that you look for the final result (which is a type of int) directly,
  // then you first need to think about the range for this value, and then find a way to search for the minimum
  // within the range.

  // The left side is the max(nums), which is the minimum of the range, the right side is sum(nums)

  // After this stage, really you model will be search for a minimum within (min(nums), sum(nums))
  // using binary search, the termination condition should be the number of cross with m.

  public boolean validate(int[] nums, long target, int m) {
    // to keep track of the current loop
    long current = 0;

    // to keep track of the loop number
    int loop = 0 ;
    for (int n : nums) {
      current += n;
      if (current > target){
        loop++;
        current = n;
      }
      if (loop >= m) {
        return false;
      }
    }
    return true;
  }

  public int splitArray(int[] nums, int m) {
    long left = 0;
    long right = 0;

    for (int n: nums) {
      if (left < n) left = n;
      right += n;
    }

    System.out.println("left and right: " + Long.toString(left) + ", " + Long.toString(right));

    while (left < right) {
      long cut = left + (right - left) / 2;

      System.out.println("Sup: " + Long.toString(cut) + ", " + validate(nums, cut, m));

      if (validate(nums, cut, m)) {
        right = cut;
        System.out.println("left and right: " + Long.toString(left) + ", " + Long.toString(right));

      } else {
        left = cut + 1;
        System.out.println("left and right: " + Long.toString(left) + ", " + Long.toString(right));

      }
    }

    return (int) left;
  }

  // Brute force with memoization, intuitive are more eaiser to understand
  // comparing with others. O((n - m) * (n - m + 1) * (m - m + 2) ... n) => O(n^m);
  public static int search(int[] nums, int start, int m, int[][] memo) {
    if (memo[start][m] > 0) {
      return memo[start][m];
    }

    // this is the last loop, sum the remaining and return;
    if (m == 1){
      int v = 0;
      for (int i = start; i < nums.length; i++){
        v += nums[i];
      }
      memo[start][m] = v;
      return v;
    }

    int sum = 0;
    int result = Integer.MAX_VALUE;
    for(int i = start; i < nums.length - 1; i++){
      sum += nums[i];
      int v = Math.max(sum, search(nums, i + 1, m - 1, memo));
      result = Math.min(result, v);
    }

    memo[start][m] = result;
    return result;
  }

  public int splitArray(int[] nums, int m) {
    // memo will be two demention, memo[i][j], i stands for left, j stands for the current m;
    int[][] memo = new int[nums.length][m+1];

    int r = search(nums, 0, m, memo);

    return r;
  }

  //====================================================================

  //TODO  A DP solution



}
