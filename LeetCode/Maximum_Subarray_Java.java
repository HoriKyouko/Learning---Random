class Solution {
    public int maxSubArray(int[] nums) {
        // everytime we find a sum greater than the previous we also store that subArray
        int currentSum = nums[0];
        int maxSum = nums[0];
        for(int i = 1; i < nums.length; i++){
            currentSum = Math.max(nums[i], currentSum+nums[i]);
            if(currentSum > maxSum) maxSum = currentSum;
        }
        return maxSum;
    }
}