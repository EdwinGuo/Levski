package Levski;
// complexity O(logn)


class SearchInRotatedSortedArray {
  public int binarySearch(int[] nums, int start, int end, int target){
    int left = start;
    int right = end;

    while(left < right){
      int mid = left + (right - left) / 2;

      if (nums[mid] > target){
        // move left
        right = mid;
      } else if (nums[mid] < target) {
        // move right
        left = mid + 1;
      } else {
        // got it, return
        return mid;
      }
    }

    return -1;
  }

  public int findPivot(int[] nums) {
    // complexity O(log n)
    int left = 0;
    int right = nums.length;
    while(left < right) {
      int mid = left + (right - left) / 2;
      if (nums[mid] >= nums[0]){
        // move right
        left = mid + 1;
      } else {
        // move left
        right = mid;
      }
    }

    if (right != nums.length) {
      return right;
    } else {
      return -1;
    }
  }

  public int search(int[] nums, int target) {
    if (nums == null || nums.length == 0) return -1;
    // first find the pivot position
    int pivotPosition = -1;

    int result = -1;

    pivotPosition = findPivot(nums);

    // now you know the range for the two parts
    if (pivotPosition == -1){
      result = binarySearch(nums, 0, nums.length, target);
    } else {
      if (target >= nums[0] && target <= nums[pivotPosition -1]) {
        result = binarySearch(nums, 0, pivotPosition, target);
      } else if (target == nums[pivotPosition]) {
        return pivotPosition;
      } else {
        result = binarySearch(nums, pivotPosition + 1, nums.length, target);
      }
    }

    return result;
  }
}
