class Solution {
    public int singleNumber(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            if(map.containsKey(nums[i]))
                map.replace(nums[i], 2);
            else
                map.put(nums[i], 1);
        }
        
       for(int i : nums)
           if(map.get(i) == 1)
               return i;
        
        return 0;
    }
}