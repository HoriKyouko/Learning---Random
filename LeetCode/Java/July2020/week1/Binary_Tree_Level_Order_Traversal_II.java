public class Binary_Tree_Level_Order_Traversal_II {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> output = new LinkedList<>();
        if(root == null)
            return output;

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while(!q.isEmpty()){
            int level = q.size();
            List<Integer> sub = new LinkedList<>();
        
            for(int i = 0; i < level; i++){
                TreeNode node = q.poll();
                if(node.left != null)
                    q.offer(node.left);
                if(node.right != null)
                    q.offer(node.right);
                sub.add(node.val);
            }
            
            output.add(0, sub);
        }
        return output;
    }
}