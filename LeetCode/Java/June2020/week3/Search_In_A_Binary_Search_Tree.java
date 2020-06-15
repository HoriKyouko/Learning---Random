public class Search_In_A_Binary_Search_Tree {
    public TreeNode searchBST(TreeNode root, int val) {
        if(root == null || root.val == val)
            return root;
        
        Queue<TreeNode> que = new LinkedList<>();
        que.offer(root);
        while(!que.isEmpty()){
            TreeNode output = que.poll();
            if(output.val == val)
                return output;
            
            if(output.left != null)
                que.offer(output.left);
            if(output.right != null)
                que.offer(output.right);
        }
        
        return null;
    }
}