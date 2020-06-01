class Solution {
    public int findKthLargest(int[] nums, int k) {
        if(nums.length == 0)
            return -1;
        Arrays.sort(nums);
        return nums[nums.length -k];
    }
}