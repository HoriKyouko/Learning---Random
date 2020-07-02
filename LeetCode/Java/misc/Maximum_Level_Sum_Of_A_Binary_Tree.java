public class Maximum_Level_Sum_Of_A_Binary_Tree {
    public int maxLevelSum(TreeNode root) {      
        int maxVal = Integer.MIN_VALUE;
        int output = 1;
        int level = 1;
        
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        
        while(!q.isEmpty()){
            int sum = 0;
            int size = q.size();
            while(size-- > 0){
                TreeNode node = q.poll();
                sum += node.val;
                if(node.left != null)
                    q.offer(node.left);
                if(node.right != null)
                    q.offer(node.right);
            }
            if(sum > maxVal){
                maxVal = sum;
                output = level;
            }
            
            level++;
        }
        return output;
    }
}