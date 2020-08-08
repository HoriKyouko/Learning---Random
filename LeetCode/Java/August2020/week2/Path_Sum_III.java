public class Path_Sum_III {
    /*
        Original solution thought process was DFS, but turned out to fail
        cause I wasn't accounting for the node itself, also thought Integer
        was by reference and it wasn't.

        Optimal Solution revolves around using a HashMap that will map each val-sum
        and will store how many occurrences there are to get that value.

        I don't fully understand how it gets the final value.
    */
    
    /*public int pathSum(TreeNode root, int sum) {
        if(root == null)
            return 0;
        return helper(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);
    }
    
    public int helper(TreeNode root, int sum){       
        if(root == null)
            return 0;
        return ((root.val == sum) ? 1 : 0) + helper(root.left, sum-root.val) + helper(root.right, sum-root.val);
    }*/
    
    public int pathSum(TreeNode root, int sum) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        return helper(root, sum, 0, map);
    }
    
    public int helper(TreeNode root, int sum, int val, HashMap<Integer, Integer> map){
        if(root == null){
            return 0;
        }
        
        val += root.val;
        int res = map.getOrDefault(val-sum, 0);
        map.put(val, map.getOrDefault(val, 0) + 1);
        
        res += helper(root.left, sum, val, map) + helper(root.right, sum, val, map);
        map.put(val, map.get(val) - 1);
        return res;
    }
    
}