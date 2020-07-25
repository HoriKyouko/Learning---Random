public class Find_Minimum_In_Sorted_Array_II {
    public int findMin(int[] nums) {
        /*
        Original O(N) Solution
        
        int output = Integer.MAX_VALUE;
        
        for(int i = 0; i < nums.length; i++){
            output = Math.min(output, nums[i]);
        }
        
        return output;*/
        
        int low = 0;
        int high = nums.length-1;
        int pivot = 0;
        while(low < high){
            pivot = low + (high-low)/2;
            if(nums[pivot] > nums[high])
                low = pivot + 1;
            else if(nums[pivot] < nums[low])
                high = pivot;
            else
                high--;
        }
        return nums[low];
    }
}