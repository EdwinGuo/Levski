package Levski;

class BubbleSort {
  //For data sets that are already sorted, or close to sorted, it offers good performance
  //orst and Average Case Time Complexity: O(n*n). Worst case occurs when array is reverse sorted.
  //Best Case Time Complexity: O(n). Best case occurs when array is already sorted.
  //  Auxiliary Space: O(1)
  //  Boundary Cases: Bubble sort takes minimum time (Order of n) when elements are already sorted.
  //  Sorting In Place: Yes
  //Stable: Yes
  public int[] sort(int[] nums){

    boolean swaped = false;
    for (int i = 0; i < nums.length - 1; i++){
      swaped = false;
      for (int j = 0; j < nums.length - i - 1; j++) {
        if (nums[j] > nums[j+1]) {
          int temp = nums[j];
          nums[j] = nums[j++];
          nums[j+1] = temp;
          swaped = true;
        }

      }

      if (!swaped) {
        break;
      }
    }
    return nums;
  }

}
