public class Maximum_Sum_Circular_Subarray {
    public int maxSubarraySumCircular(int[] A) {
        if(A == null || A.length == 0) return 0;
        
        int currentSum = A[0];
        int maxSum = A[0];
        int maxTotal = A[0];
        int minSum = A[0];
        int minTotal = A[0];
        
        for(int i = 1; i < A.length; i++){
            if(maxSum + A[i] > A[i]) maxSum += A[i];
            else maxSum = A[i];
            
            maxTotal = Math.max(maxTotal, maxSum);
            
            if(minSum + A[i] < A[i]) minSum += A[i];
            else minSum = A[i];
            
            minTotal = Math.min(minTotal, minSum);
            
            currentSum += A[i];
        }
        
        if(currentSum == minTotal) return maxTotal;
        
        return Math.max(currentSum-minTotal, maxTotal);
    }
}