class Solution {
    public int findJudge(int N, int[][] trust) {
        if(N == 1) return N;
        
        int [] score = new int[N+1];
        
        for(int [] i: trust){
            score[i[1]]++;
            score[i[0]]--;
        }
        
        for(int i = 1; i <= N; i++)
            if(score[i] == N-1)
                return i;
        
        return -1;
    }
}