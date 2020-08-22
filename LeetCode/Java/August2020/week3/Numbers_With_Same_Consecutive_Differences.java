public class Numbers_With_Same_Consecutive_Differences {
    public int[] numsSameConsecDiff(int N, int K) {
        if(N == 1)
            return new int[] {0,1,2,3,4,5,6,7,8,9};
        
        List<Integer> output = new ArrayList<>();
        for(int i = 1; i < 10; i++)
            dfs(N-1, i, K, output);
        
        return output.stream().mapToInt(i->i).toArray();
    }
    
    public void dfs(int N, int num, int K, List<Integer> output){
        if(N == 0){
            output.add(num);
            return;
        }
        
        List<Integer> nextDigits = new ArrayList<>();
        
        Integer tailDigit = num % 10;
        nextDigits.add(tailDigit + K);
        
        if(K != 0)
            nextDigits.add(tailDigit - K);
        
        for(Integer nextDigit : nextDigits){
            if(0 <= nextDigit && nextDigit < 10){
                Integer nextNum = num * 10 + nextDigit;
                this.dfs(N-1, nextNum, K, output);
            }
        }
    }
}