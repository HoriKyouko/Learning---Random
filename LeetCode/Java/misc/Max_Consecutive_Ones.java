public class Max_Consecutive_Ones {
    public int findMaxConsecutiveOnes(int[] nums) {
        int output = 0;
        int len = 0;
        
        for(int i = 0; i < nums.length; i++){
            if(nums[i] == 0){
                output = Math.max(output, len);
                len = 0;
            }
            else{
                len++;
            }
        }
        
        return Math.max(output, len);
    }
}