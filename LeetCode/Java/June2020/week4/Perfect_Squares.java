public class Perfect_Squares {
    public int numSquares(int n) {
        int[] dp = new int[n+1];
        
        for(int i = 1; i <= n; i++){
            int min = i;
            int y = 1;
            int sq = 1;
            while(sq <= i){
                min = Math.min(min, 1 + dp[i-sq]);
                y++;
                sq = y*y;
            }
            dp[i] = min;
        }
        
        return dp[n];
    }
}