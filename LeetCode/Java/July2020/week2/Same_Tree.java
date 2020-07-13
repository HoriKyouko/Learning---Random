public class Same_Tree {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p == null && q == null) return true;
        else if(p == null && q != null || p !=null && q == null) return false;
        else if(p.val != q.val) return false;
        
        boolean leftResult = isSameTree(p.left, q.left);
        boolean rightResult = isSameTree(p.right, q.right);
        
        return leftResult && rightResult;
    }
}