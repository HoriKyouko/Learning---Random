class Solution {
    int output = 0;
    int count = 0;
    
    public int kthSmallest(TreeNode root, int k) {
        helper(root, k);
        return output;
    }
    
    private void helper(TreeNode root, int k){
        if(root == null) return;
        
        helper(root.left, k);
        count++;
        if(count == k){
            output = root.val;
            return;
        }
        helper(root.right, k);
    }
}