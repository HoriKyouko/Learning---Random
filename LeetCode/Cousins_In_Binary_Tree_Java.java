class Solution {
    int xDepth;
    int yDepth;
    TreeNode xRoot;
    TreeNode yRoot;
    
    public boolean isCousins(TreeNode root, int x, int y) {
        preOrderTraversal(root, null, x, y, 0);
        
        if(xDepth == yDepth && xRoot.val != yRoot.val)
            return true;
        return false;
    }
    
    private void preOrderTraversal(TreeNode node, TreeNode root, int x, int y, int depth){
        if(node == null) return;
        
        if(node.val == x){
            xDepth = depth;
            xRoot = root;
        }
        if(node.val == y){
            yDepth = depth;
            yRoot = root;
        }
        preOrderTraversal(node.left, node, x, y, depth+1);
        preOrderTraversal(node.right, node, x, y, depth+1);
    }
}