public class Binary_Tree_Maximum_Path_Sum {
    int output;
    public int maxPathSum(TreeNode root) {
        output = Integer.MIN_VALUE;
        pathSum(root);
        return output;
    }
    
    private int pathSum(TreeNode root){
        if(root == null)
             return 0;
        int left = Math.max(0, pathSum(root.left));
        int right = Math.max(0, pathSum(root.right));
        output = Math.max(output, left + right + root.val);
        return Math.max(left, right) + root.val;
    }
}