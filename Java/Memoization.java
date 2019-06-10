import java.util.*;
public class Memoization{
    public static void main(String[] args) {
        Memoization memo = new Memoization();
        int[] fibs = new int[31];
        System.out.println(memo.fib(30, fibs));
        System.out.println(memo.fibBottomUp(30));
    }

    int fib(int n, int[] memo){
        if(memo[n] != 0) return memo[n];
        int result;
        if(n == 1 || n == 2) result = 1;
        else result = fib(n-1, memo) + fib(n-2, memo);
        memo[n] = result;
        return result;
    }

    int fibBottomUp(int n){
        if(n == 1 || n == 2) return 1;
        int [] bottomUp = new int[n+1];
        bottomUp[1] = 1;
        bottomUp[2] = 1;
        for(int i = 3; i <= n; i++)
            bottomUp[i] = bottomUp[i-1] + bottomUp[i-2];
        return bottomUp[n];
    }
}