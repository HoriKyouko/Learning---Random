public class Two_City_Scheduling {
    public int twoCitySchedCost(int[][] costs) {
        int N = costs.length / 2;
        int[][] memo = new int[N+1][N+1];
        
        for(int i = 1; i <= N; i++)
            memo[i][0] = memo[i-1][0] + costs[i-1][0];
        
        for(int i = 1; i <= N; i++)
            memo[0][i] = memo[0][i-1] + costs[i-1][1];
        
        for(int i = 1; i <= N; i++)
            for(int j = 1; j <= N; j++)
                memo[i][j] = Math.min(memo[i-1][j] + costs[i+j-1][0], memo[i][j-1] + costs[i+j-1][1]);
        
        return memo[N][N];
    }
}