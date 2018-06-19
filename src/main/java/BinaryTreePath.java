/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public List<String> binaryTreePaths(TreeNode root) {
        // Using a recursive function to walk left and right
        // The termination condition is that when both left and right are null,
        // then we add the string to the result
        List<String> result = new ArrayList<String>();
        if (root == null) return result;
        
        findThePath(root, result, "");
        return result;
    }
    
    public void findThePath(TreeNode root, List<String> result, String str){
        if ((root.left == null && root.right == null)) {
            result.add(str.isEmpty() ? Integer.toString(root.val) : (str + "->" + root.val));
            return;
        }
        if (root.left != null) findThePath(root.left, result, str.isEmpty() ? Integer.toString(root.val) : (str + "->" + root.val));
        if (root.right != null) findThePath(root.right, result, str.isEmpty() ? Integer.toString(root.val) : (str + "->" + root.val));
    }
    
    
}
