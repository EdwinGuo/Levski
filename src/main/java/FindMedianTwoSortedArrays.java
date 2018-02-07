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
  public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    if (nums1.length == 0 && nums2.length == 0) return 0.0;

    System.out.println(Arrays.toString(nums1));
    System.out.println(Arrays.toString(nums2));
    if (nums1.length == 0 || nums1 == null) return nums2.length % 2 == 0 ? ((double) (nums2[nums2.length/2 -1] + nums2[nums2.length/2])) / 2 : (double) nums2[nums2.length/2];
    if (nums2.length == 0 || nums2 == null) return nums1.length % 2 == 0 ? ((double) (nums1[nums1.length/2 -1] + nums1[nums1.length/2])) / 2 :(double) nums1[nums1.length/2];

    // Make sure use the shorter one as the anchor array
    if (nums1.length > nums2.length) {
      return findMedianSortedArrays(nums2, nums1);
    }

    // Taking care of extreme case here
    if (nums1[nums1.length - 1] < nums2[0]) {
      return calEdgeCase(nums1, nums2);
    }

    if (nums1[0] > nums2[nums2.length - 1]) {
      return calEdgeCase(nums2, nums1);
    }

    // another edge case is the shorter array only contain 1 element
    if (nums1.length == 1 || nums2.length == 1) return calEdgeOneCase(nums1, nums2);


    System.out.println(Arrays.toString(nums1));
    System.out.println(Arrays.toString(nums2));

    return cutArray(nums1, nums2, new Node(0, nums1.length));
  }

  public double cutArray(int[] arr1, int[] arr2, Node cut) {
    int cut1 =cut.left + (cut.right - cut.left) / 2;
    int s1 = arr1.length;
    int s2 = arr2.length;
    int s = s1 + s2;

    System.out.println("cut" + Integer.toString(cut1));

    int Ul = cut1 == 0 ?  arr2[s/2 - cut1 - 1] : arr1[cut1 -1];
    int Ur = cut1 == (arr1.length -1) ? arr2[s/2 - cut1] : arr1[cut1];

    int Dl = arr2[s/2 - cut1 - 1];
    int Dr = arr2[s/2 - cut1];

    System.out.println("ul" + Integer.toString(Ul) + ", ur" + Integer.toString(Ur));
    System.out.println("Dl" + Integer.toString(Dl) + ", dr" + Integer.toString(Dr));

    if (Ul > Dr) {
      // move cut to the left
      System.out.println("move left");

      cutArray(arr1, arr2, new Node(cut.left, cut1 ));
    }

    if (Ur < Dl) {
      // move cut to the right
      System.out.println("move right");

      cutArray(arr1, arr2, new Node(cut1 , cut.right));

    }

    // if the above two condition pass, then there is the median and we can start return the result
    if (s % 2 == 1) {
      return (double) Math.max(Ul, Dl);
    } else {
      double r = (double) (Math.max(Ul, Dl) + Math.min(Ur,Dl));
      return r / 2;
    }
  }

  public double calEdgeOneCase(int[] nums1, int[] nums2) {
    if (nums1.length == 1 && nums2.length == 1) return (double) (nums1[0] + nums2[0]) / 2;

    if (nums2.length == 1) return calEdgeOneCase(nums2, nums1);
    if ((nums1.length + nums2.length) % 2 == 0) {

      if (nums1[0] <= nums2[nums2.length / 2]) {
        double r = (double) Math.max(nums1[0] , nums2[nums2.length / 2 - 1]) + nums2[nums2.length / 2];
        return r / 2;
      } else if (nums1[0] <= nums2[nums2.length / 2]) {
        double r = Math.min(nums1[0] , nums2[nums2.length / 2 + 1]) + nums2[nums2.length / 2];
        return r / 2;
      }
    } else {
      if (nums1[0] >= nums2[nums2.length / 2 - 1] && nums1[0] <= nums2[nums2.length / 2])
        return nums1[0];
      else if (nums1[0] >= nums2[nums2.length / 2]) return nums2[nums2.length / 2];
      else return nums2[nums2.length / 2 - 1];

    }
    return 0.0;
  }

  public double calEdgeCase(int[] nums1, int[] nums2) {
    int[] longer;
    int[] shorter;
    if (nums1.length == nums2.length) {
      double r = (double) nums1[nums1.length - 1] + nums2[0];
      return r / 2;
    }
    else if (nums1.length > nums2.length) {
      longer = nums1;
      shorter = nums2;

      if ((longer.length + shorter.length ) % 2 == 0) {
        double r = (double) (longer[(longer.length + shorter.length)/2 - 1] + longer[(longer.length + shorter.length)/2]);
        return r /2;
      } else {
        return (double) (longer[(longer.length + shorter.length)/2 - 1]);
      }

    } else {
      longer = nums2;
      shorter = nums1;

      if ((longer.length + shorter.length ) % 2 == 0) {
        double r = (double) (longer[(longer.length + shorter.length)/2 - shorter.length - 1] + longer[(longer.length + shorter.length)/2 - shorter.length]);
        return r / 2;
      } else {
        double r = (double) longer[(longer.length + shorter.length)/2 - shorter.length];
        return r;
      }
    }

  }


  class Node {
    int left;
    int right;
    public Node(int l, int r) {
      left = l;
      right = r;
    }
  }


}
