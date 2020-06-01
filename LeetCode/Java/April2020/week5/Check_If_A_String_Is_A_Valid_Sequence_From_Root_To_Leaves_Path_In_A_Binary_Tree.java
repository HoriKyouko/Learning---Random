public class Check_If_A_String_Is_A_Valid_Sequence_From_Root_To_Leaves_Path_In_A_Binary_Tree {
    public boolean isValidSequence(TreeNode root, int[] arr){
        if(root == null) return arr.length == 0;
        return isValid(root, arr, 0);
    }

    private boolean isValid(TreeNode root, int[] arr, int idx){
        if(root.val != arr[idx]) return false;
        if(idx == arr.length-1)
            return root.left == null && root.right == null;
        return ( (root.left != null && isValid(root.left, arr, idx + 1)) || (root.right != null && isValid(root.right, arr, idx+1)) );
    }
}