public class Uncrossed_Lines {
    public int maxUncrossedLines(int[] A, int[] B) {
        int m = A.length;
        int n = B.length;
        int [][] memo = new int[m+1][n+1];
        
        for(int i = 1; i <= m; i++){
            for(int j = 1; j <= n; j++){
                if(A[i-1] == B[j-1])
                    memo[i][j] = 1 + memo[i-1][j-1];
                else
                    memo[i][j] = Math.max(memo[i-1][j], memo[i][j-1]);
            }
        }
        
        return memo[m][n];
    }
}