class Solution {
    public int fib(int N) {
        if(N == 0) return 0;
        else if(N == 1 || N == 2) return 1;
        
        int first = 1;
        int second = 1;
        
        for(int i = 3; i < N; i++){
            int temp = first + second;
            first = second;
            second = temp;
        }
        
        return first + second;
    }
}