public class Sum_Of_Left_Leaves {
    public int sumOfLeftLeaves(TreeNode root) {
        // if root is null return 0
        if(root == null)
            return 0;
        // current answer value is 0
        int ans = 0;
        // if our roots left node isn't null check it
        if(root.left != null){
            // if the left nodes left and right children are null then its a leaf and we need to 
            // add its value ot the output
            if(root.left.left == null && root.left.right == null)
                ans += root.left.val;
            // if its left or right isn't null then check the left node.
            // the right node gets checked outside of this if condition.
            else
                ans += sumOfLeftLeaves(root.left);
        }
        // checks the right node and adds whatever value it gets from it's recursive call.
        ans += sumOfLeftLeaves(root.right);
        // returns the ans for the rest of the recursive tree/final solution.
        return ans;
    }
}