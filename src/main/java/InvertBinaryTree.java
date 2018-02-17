package Levski;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

// Invert a binary tree.
//   4
//   /   \
//   2     7
//   / \   / \
//   1   3 6   9

//   to
//   4
//   /   \
//   7     2
//   / \   / \
//   9   6 3   1

class InvertBinaryTree {

  public void swap(TreeNode current){
    // make sure that at least one child node is not null
    // Otherwrise, return the result
    if (current == null) return;
    if (current.left == null && current.right == null) return;

    TreeNode temp;
    temp = current.right;
    current.right = current.left;
    current.left = temp;

    swap(current.left);
    swap(current.right);
  }

  public TreeNode invertTree(TreeNode root) {
    if (root == null || (root.left == null && root.right == null)) return root;

    swap (root);
    return root;
  }

}
