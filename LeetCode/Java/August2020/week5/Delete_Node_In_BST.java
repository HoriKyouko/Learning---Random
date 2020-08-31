public class Delete_Node_In_BST {
    public TreeNode deleteNode(TreeNode root, int key) {
        if(root == null)
            return root;
        if(root.val == key){
            if(root.left == null)
                return root.right;
            else if(root.right == null)
                return root.left;
            else{
                TreeNode temp = findMin(root.right);
                root.val = temp.val;
                root.right = deleteNode(root.right, root.val);
            }
        }
        else if(key < root.val)
            root.left = deleteNode(root.left, key);
        else
            root.right = deleteNode(root.right, key);
        return root;
    }
    
    public TreeNode findMin(TreeNode root){
        while(root.left != null)
            root = root.left;
        return root;
    }
}