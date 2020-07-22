public class Binary_Tree_Zigzag_Level_Order_Traversal {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> output = new ArrayList<>();
        if(root == null)
            return output;
        
        // false means -> right to left, true means -> left to right
        boolean flag = true;
        LinkedList<TreeNode> link = new LinkedList<>();
        link.add(root);
        while(!link.isEmpty()){
            int size = link.size();
            LinkedList<Integer> sub = new LinkedList<>();
            if(flag){
                for(int i = 0; i < size; i++){
                    TreeNode temp = link.remove();
                    sub.addFirst(temp.val);
                    if(temp.right != null){
                        link.add(temp.right);
                    }
                    if(temp.left != null){
                        link.add(temp.left);
                    }
                }
            }
            else{
                for(int i = 0; i < size; i++){
                    TreeNode temp = link.remove();
                    sub.add(temp.val);
                    if(temp.right != null){
                        link.add(temp.right);
                    }
                    if(temp.left != null){
                        link.add(temp.left);
                    }
                }
            }
            output.add(sub);
            flag = !flag;
        }
        return output;
    }
}