public class Single_Element_In_A_Sorted_Array {
    public int singleNonDuplicate(int[] nums) {
        int start = 0;
        int end = nums.length-1;
        int mid = 0;
        while(start < end){
            mid = start + (end - start) / 2;
            if((mid-start) % 2 == 0 && (end-mid) % 2 == 0)
                return nums[mid];
            else if((mid - start) % 2 == 1)
                end = mid;
            else
                start = mid + 1;
                
            /*if(nums[start] == nums[start + 1]) start += 2;
            else return nums[start];
            if(nums[end] == nums[end-1]) end -= 2;
            else return nums[end];*/
        }
        
        return nums[mid];
    }
}