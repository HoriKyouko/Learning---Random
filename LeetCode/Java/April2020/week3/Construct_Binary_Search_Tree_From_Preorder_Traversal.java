public class Construct_Binary_Search_Tree_From_Preorder_Traversal {
    public TreeNode bstFromPreorder(int[] preorder) {
        TreeNode root = new TreeNode(preorder[0]);
        for(int i = 1; i < preorder.length; i++)
            constructBST(root, preorder[i]);
        
        return root;   
    }
    
    private void constructBST(TreeNode root, int value){
        /*
            First check if our roots value is less than the value
            we want to put in.
            
            If it is we then check if the roots right branch is null and
            if so put it in the right branch.
            else if not we call constructBST on the right node and value.
            
            If roots value is greater than value we want to put in we check
            if the left branch is null and if so we put it in the left branch.
            else if not we call constructBST on the left node and value.
        */   
        if(root.val < value){
            if(root.right == null){
                TreeNode node = new TreeNode(value);
                root.right = node;
                return;
            }
            else{
                constructBST(root.right, value);
            }
        }
        else if(root.val > value){
            if(root.left == null){
                TreeNode node = new TreeNode(value);
                root.left = node;
                return;
            }
            else{
                constructBST(root.left, value);
            }
        }
    }
}