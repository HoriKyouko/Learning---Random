public class Construct_Binary_Tree_From_Inorder_and_Postorder_Traversal {
    // Ending of postorder will always be a root,
    // Traversing through our inorder till we find our roots value and that is our
    // right subtree endpoint in the inorder array, and the beginning of our left
    // subtrees startpoint in the inorder array.
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return buildTree(inorder, inorder.length-1, 0, postorder, postorder.length-1);
    }
    
    private TreeNode buildTree(int[] inorder, int inStart, int inEnd, int[] postorder, int postStart){
        if(postStart < 0 || inStart < inEnd)
            return null;
        TreeNode root = new TreeNode(postorder[postStart]);
        int rIndex = inStart;
        for(int i = inStart; i >= inEnd; i--){
            if(inorder[i] == root.val){
                rIndex = i;
                break;
            }
        }
        root.right = buildTree(inorder, inStart, rIndex+1, postorder, postStart-1);
        root.left = buildTree(inorder, rIndex-1, inEnd, postorder, postStart-(inStart-rIndex)-1);
        return root;
    }
}