public class Maximum_Width_Of_A_Binary_Tree {
    public int widthOfBinaryTree(TreeNode root) {
        if(root == null)
            return 0;
        Queue<TreeNode> que = new LinkedList<>();
        HashMap<TreeNode, Integer> map = new HashMap<>();
        int maxWidth = 0;
        que.offer(root);
        map.put(root, 1);
        while(!que.isEmpty()){
            int size = que.size();
            int start = 0;
            int end = 0;
            for(int i = 0; i < size; i++){
                TreeNode t = que.poll();
                if(i == 0)
                    start = map.get(t);
                if(i == size-1)
                    end = map.get(t);
                if(t.left != null){
                    que.offer(t.left);
                    map.put(t.left, map.get(t) * 2);
                }
                if(t.right != null){
                    que.offer(t.right);
                    map.put(t.right, map.get(t) * 2+1);
                }
            }
            maxWidth = Math.max(maxWidth, end-start + 1);
        }
        
        return maxWidth;
    }
}