package Levski;

import java.util.*;

class FindMedianTwoSortedArrays {
  // There are two sorted arrays nums1 and nums2 of size m and n respectively.
  // Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
  // Example 1:
  // nums1 = [1, 3]
  // nums2 = [2]
  // The median is 2.0
  // Example 2:
  // nums1 = [1, 2]
  // nums2 = [3, 4]
  // The median is (2 + 3)/2 = 2.5


  // Example
  //             Ul   Ur
  // Array1 => 1, 3,| 4, 6, 10
  //                     Dl  Dr
  // Array2 => 2, 5, 7, 11,| 12, 15

  // hint: https://leetcode.com/problems/median-of-two-sorted-arrays/description/
  // https://leetcode.com/problems/median-of-two-sorted-arrays/discuss/2616/Another-simple-and-neat-solution-binary-search-non-recursion-3-rows-of-core-code-O(log(min(m-n)))
  // https://leetcode.com/problems/median-of-two-sorted-arrays/discuss/2499/Share-my-simple-O(log(m+n))-solution-for-your-reference

  // After observing pattern, we can confirm that:
  // 1. Consider make a cut on each of the int array, the array1's left remaining size +
  // array2's left remaining size has to equal to array1's right remaining size + array2's right remaining size
  // 2. Ul has to less then Dr (otherwise, move array1 cut to the left, array2 cut to the right),   Ur has to greater then Dl
  // (otherwise, move array1 cut to the right, array2 cut to the left),
  // 3. if size(array1 + array2) / 2 == 0 (as in size is even), then median = (max(Ul, Dl) + min(Ur, Dl))/ 2
  // if size(array1 + array2) / 2 == 1 (as in size is odd), then median = max(Ul, Dl)

  // arr1.length < arr2.length, only need to remember cut1.
  public double cutArray(int[] arr1, int[] arr2, Node cut) {
    int cut1 =(cut.right - cut.left) / 2;
    int s1 = nums1.length;
    int s2 = nums2.length;
    int s = nums1 + nums2;

    int Ul = s1[cut1 -1];
    int Ur = s1[cut1];

    int Dl = s2[s/2 - cut1];
    int Dr = s2[s/2 - cut1 + 1];

    if (Ul > Dr) {
      // move cut to the left
      cutArray(arr1, arr2, new Node(cut.left, cut1 - 1));
    }

    if (Ur < Dl) {
      // move cut to the right
      cutArray(arr1, arr2, new Node(cut - 1, cut.right));
    }

    // if the above two condition pass, then there is the median and we can start return the result
    if (s / 2 == 1) {
      return Double.toDouble(Math.max(arr1[Ul], arr2[Dl]));
    } else {
      return Double.toDouble((Math.max(arr1[Ul], arr2[Dl]) + Math.min(arr1[Ur], arr2[Dl])))/2;
    }
  }

  public double calEdgeCase(int[] nums1, int[] nums2) {
    // case 1: if there is no intersect between two array
    if (nums1[num1.length - 1] < nums2[0] ) {
      if ((nums1.length + nums2.length ) / 2 == 0) {
        if (nums1.length == nums2.length) {
          return Double.toDouble(nums1[num1.length - 1] + nums2[0]) / 2;
        } else {
          return Double.toDouble(nums2[(nums2.length + nums1.length)/2 - nums1.length - 1] + nums2[(nums2.length + nums1.length)/2 - nums1.length]) / 2;
        }
      } else {
        return Double.toDouble(nums2[(nums2.length + nums1.length)/2 - nums1.length]);
      }
    }
  }

  public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    if (nums1.length == 0 || nums1 == null) return nums2.length / 2 == 0 ? (nums2[nums2.length/2 -1] + nums2[nums2.length/2]) / 2 : nums2[nums2.length/2];
    if (nums2.length == 0 || nums2 == null) return nums1.length / 2 == 0 ? (nums1[nums1.length/2 -1] + nums1[nums1.length/2]) / 2 : nums2[nums1.length/2];

    // Make sure use the shorter one as the anchor array
    if (nums1.length > nums2.length) {
      findMedianSortedArrays(nums2, nums1);
    }

    // Taking care of extreme case here
    if (nums1[num1.length - 1] < nums2[0]) {
      return calEdgeCase(nums1, nums2);
    }

    if (nums1[0] > nums2[nums2.length - 1]) {
      return calEdgeCase(nums2, nums1);
    }

    // another edge case is the shorter array only contain 1 element
    // TODO

    return cutArray(nums1, nums2, new Node(0, nums1.length));
  }

  public class Node {
    int left;
    int right;
    public Node(int l, int r) {
      left = l;
      right = r;
    }
  }


}
