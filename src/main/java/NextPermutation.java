package Levski;

class NextPermuation {
  //6:32
  // 2 5 8 6 4 3
  //   l   r
  public void swap(int[] nums, int p1, int p2) {
    int temp = nums[p1];
    nums[p1] = nums[p2];
    nums[p2] = temp;
  }

  public void flip(int[] nums, int start, int end){
    while(start < end) {
      swap(nums, start , end);
      start++;
      end--;
    }
  }

  public void nextPermutation(int[] nums) {
    if (nums == null || nums.length == 0) return;
    int swapLeft = -1;
    int swapRight = -1;

    for(int i = nums.length - 1; i >=1 ; i--){
      if (nums[i-1] < nums[i]) {
        swapLeft = i-1;
        break;
      }
    }

    if (swapLeft == -1){
      // means nums is in a decending order, flip the whole thing
      flip(nums, 0, nums.length - 1);
    } else {

      // otherwise, find the swapRight position and do the swap,
      // flip swapLeft to nums.length - 1
      for(int i = nums.length - 1; i >= swapLeft; i--){
        if (nums[i] > nums[swapLeft]){
          swapRight = i;
          swap(nums, swapLeft, swapRight);
          flip(nums, swapLeft + 1, nums.length - 1);
          return;
        }
      }
    }
  }
}
