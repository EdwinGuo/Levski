package Levski;

class FindMedianTwoBetter {
  // To improve the previous version of implementation
  public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    int n1 = nums1.length;
    int n2 = nums2.length;

    if (n1 > n2) return findMedianSortedArrays(nums2, nums1);

    int len = (n1 + n2 + 1) / 2;

    // define the scope of the BS in the shorter array
    int low = 0;
    int high = n1;

    while(low < high) {
      // cut position on the short
      int scut = low + (high - low) / 2;
      int lcut = len - scut;

      if (nums1[scut] < nums2[lcut -1]) {
        // move right, binary search condition: [low, high);
        low = scut + 1;
      }
      else {
        high = scut;
      }
    }

    int shortleft = low == 0 ? Integer.MIN_VALUE : nums1[low-1];
    int shortright = low == 0 ? Integer.MAX_VALUE : nums1[low];
    int longleft = low == 0 ? Integer.MIN_VALUE : nums2[len - low-1];
    int longright = low == 0 ? Integer.MAX_VALUE : nums2[len - low];

    if ((n1 + n2) % 2 ==0) {
      return (Math.max(shortleft, longleft) + Math.min(shortleft, longright)) * 0.5;
    } else {
      return (double) Math.max(shortleft, longleft);
    }



  }
}
