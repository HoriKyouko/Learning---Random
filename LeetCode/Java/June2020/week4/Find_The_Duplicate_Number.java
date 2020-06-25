public class Find_The_Duplicate_Number {
    /*
    
    This is the solution I came up with which breaks the O(1)
    space complexity, but after looking at the solution I agree
    I would not have thought of cycle detection in an interview
    setting, especially after never seeing it before.
    
    public int findDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for(int num : nums)
            if(!set.add(num))
                return num;
        return -1;
    }
    */

    /**
     * Cycle Detection algorithm:
     */
    public int findDuplicate(int[] nums){
        int rabbit = 0;
        int tortoise = 0;
        do{
            rabbit = nums[nums[rabbit]];
            tortoise = nums[tortoise];
        }while(rabbit != tortoise);

        tortoise = 0;
        
        while(rabbit != tortoise){
            rabbit = nums[rabbit];
            tortoise = nums[tortoise];
        }
        return rabbit;
    }
}