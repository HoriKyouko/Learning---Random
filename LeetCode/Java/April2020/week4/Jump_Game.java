public class Jump_Game {
    public boolean canJump(int[] nums) {
        int output = nums.length - 1;
        for(int i = output; i >= 0; i--)
            if(i + nums[i] >= output)
                output = i;
        
        return output == 0;
    }
}