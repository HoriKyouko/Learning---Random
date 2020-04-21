class Solution {
    public int[] sortedSquares(int[] A) {
        int l = 0;
        int r = A.length-1;
        int[] output = new int[A.length];
        
        for(int i = A.length-1; i >= 0; i--){
            if(Math.abs(A[l]) > A[r]){
                output[i] = A[l] * A[l];
                l++;
            }
            else{
                output[i] = A[r] * A[r];
                r--;
            }
        }
        
        return output;
    }
}