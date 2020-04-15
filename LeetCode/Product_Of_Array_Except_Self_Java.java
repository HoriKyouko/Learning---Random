/**
 * Completed with help from Nick Whites video with a 
 * better explanation than the solutions.
 * 
 * Video: https://www.youtube.com/watch?v=khTiTSZ5QZY
 * 
 */
class Solution {
    public int[] productExceptSelf(int[] nums) {
        /*int [] output = new int [nums.length];
        int [] left = new int [nums.length];
        int [] right = new int [nums.length];
        
        left[0] = 1;
        right[nums.length - 1] = 1;
        
        for(int i = 1; i < nums.length; i++){
            left[i] = nums[i-1] * left[i-1];
        }
        
        for(int i = nums.length-2; i >= 0; i--){
            right[i] = nums[i+1] * right[i+1];
        }
        
        for(int i = 0; i < nums.length; i++){
            output[i] = left[i] * right[i];
        }
        
        return output;*/
        
        int[] output = new int[nums.length];
        output[0] = 1;
        
        for(int i = 1; i < nums.length; i++){
            output[i] = output[i-1] * nums[i-1];
        }
        int r = 1;
        for(int i = nums.length-1; i >= 0; i--){
            output[i] = output[i] * r;
            r = r * nums[i];
        }
        return output;
    }
}