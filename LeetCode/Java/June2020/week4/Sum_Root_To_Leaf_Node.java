public class Sum_Root_To_Leaf_Node {
    int output = 0;
    public int sumNumbers(TreeNode root) {
        if(root == null)
            return output;
        traverse(root, 0);
        return output;
    }
    
    private void traverse(TreeNode root, int value){
        if(root.left == null && root.right == null){
            output += value + root.val;
            return;
        }
        value += root.val;
        if(root.left != null)
            traverse(root.left, value*10);
        if(root.right != null)
            traverse(root.right, value*10);
    }
}