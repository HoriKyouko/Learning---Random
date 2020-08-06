public class Find_All_Duplicates_In_An_Array{
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> output = new ArrayList<>();
        for(int n : nums){
            int lookUp = nums[Math.abs(n) - 1];
            if(lookUp < 0)
                output.add(Math.abs(n));
            else
                nums[Math.abs(n)-1] = nums[Math.abs(n)-1]*-1;
        }
        return output;
    }
}