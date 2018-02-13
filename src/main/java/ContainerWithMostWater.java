package Levski;

class ContainerWithMostWater {
  //Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai). n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together with x-axis forms a container, such that the container contains the most water.
  //Note: You may not slant the container and n is at least 2.

  public int maxArea(int[] height) {
    if (height.length == 0 || height.length == 1) return 0;
    int result = 0;
    int l = 0;
    int r = height.length - 1;

    while(l < r){
      int current = Math.min(height[l], height[r]) * (r-l);
      if (current > result)
        result = current;

      if (height[l] > height[r]){
        // move r to r - 1
        r--;
      } else {
        l++;
      }
    }
    return result;
  }

}
