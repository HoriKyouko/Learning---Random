public class Find_All_Numbers_Disappeared_In_A_Array{
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> output = new ArrayList<>();
        
        for(int i = 0; i < nums.length; i++){
            int val = Math.abs(nums[i]) - 1;
            if(nums[val] > 0)
                nums[val] = -nums[val];
        }
        
        for(int i = 0; i < nums.length; i++)
            if(nums[i] > 0)
                output.add(i+1);
        
        return output;
    }
}