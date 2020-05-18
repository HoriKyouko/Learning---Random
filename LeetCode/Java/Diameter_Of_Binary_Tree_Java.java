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
    int val;
    public int diameterOfBinaryTree(TreeNode root) {
        val = 1;
        traverse(root);
        return val - 1;
    }

    public int traverse(TreeNode root){
        if(root == null)
            return 0;
        
        int l = traverse(root.left);
        int r = traverse(root.right);
        val = Math.max(val, l+r+1);
        return Math.max(l,r) + 1;
    }
}