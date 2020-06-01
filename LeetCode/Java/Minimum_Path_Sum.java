public class Minimum_Path_Sum {
    public int minPathSum(int[][] grid) {
        int[][] dp = new int[grid.length][grid[0].length];
        
        int count = 0;
        
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                dp[i][j] += grid[i][j];
                // Account for looking up but not left
                if(i-1 >= 0 && j-1 < 0)
                    dp[i][j] += dp[i-1][j];
                // Account for looking left but not up
                else if(i-1 < 0 && j-1>= 0)
                    dp[i][j] += dp[i][j-1];
                // Account for looking left and up.
                else if(i-1 >= 0 && j-1 >= 0)
                    dp[i][j] += Math.min(dp[i-1][j], dp[i][j-1]);
            }
        }
        
        return dp[grid.length-1][grid[0].length-1];
    }
}