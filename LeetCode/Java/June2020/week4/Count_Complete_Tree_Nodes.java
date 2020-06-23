public class Count_Complete_Tree_Nodes {
    public int countNodes(TreeNode root) {
        int height = findHeight(root);
        return height < 0 ? 0 : findHeight(root.right) == height - 1 ? (1<<height) + countNodes(root.right) : (1<< height-1) + countNodes(root.left);
    }
    
    private int findHeight(TreeNode root){
        return root == null ? -1 : 1 + findHeight(root.left);
    }
}