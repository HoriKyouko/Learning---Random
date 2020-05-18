/**
 * Completed with help from CS Dojo video with a 
 * better explanation than the solutions.
 * 
 * Video: https://www.youtube.com/watch?v=qli-JCrSwuk
 * 
 */

class Solution {
    public int numDecodings(String s) {
        int[] memo = new int[s.length() + 1];
        return helper(s, s.length(), memo);
    }
    
    private int helper(String s, int k, int[] memo){
        if(k == 0) return 1;
        int index = s.length() - k;
        if(s.charAt(index) == '0') return 0;
        
        if(memo[k] != 0) return memo[k];        
        
        int result = helper(s, k-1, memo);
        
        if(k >= 2){
            int temp = Integer.parseInt(s.substring(index, index+2));
            if(temp <= 26)
                result += helper(s, k-2, memo);
        }
        
        memo[k] = result;
        return result;
    }
}