package Levski;

//Given a set of distinct integers, nums, return all possible subsets (the power set).

// Note: The solution set must not contain duplicate subsets.

//   For example,
//   If nums = [1,2,3], a solution is:

//   [
//    [3],
//    [1],
//    [2],
//    [1,2,3],
//    [1,3],
//    [2,3],
//    [1,2],
//    []]

class Subsets {
  public List<List<Integer>> copy(List<List<Integer>> data) {
    List<List<Integer>> result = new ArrayList();

    for (List<Integer> temp: data) {
      List<Integer> w = new ArrayList();
      for (int x : temp) {
        w.add(x);
      }
      result.add(w);
    }

    return result;
  }

  public List<List<Integer>> subsets(int[] nums) {
    if (nums == null || nums.length == 0) return null;
    List<List<Integer>> result = new ArrayList();

    for (int num : nums){
      List<List<Integer>> temp = copy(result);
      for (List<Integer> res : temp){
        res.add(num);
      }

      for(List<Integer> res : temp) {
        result.add(res);
      }

      List<Integer> last =  new ArrayList();
      last.add(num);
      result.add(last);
    }

    List<Integer> em = new ArrayList();
    result.add(em);
    return result;
  }
}
