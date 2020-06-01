public class Search_In_A_Rotated_Sorted_Array {
    public int search(int[] nums, int target) {        
        int pivot = findPivot(nums, 0, nums.length-1);
        
        if(pivot == -1) return -1;
        
        if(nums[pivot] == target) return pivot;
        
        if(nums[0] <= target)
            return binarySearch(nums, 0, pivot-1, target);
        return binarySearch(nums, pivot+1, nums.length-1, target);
    }
    
    private int findPivot(int[] nums, int low, int high){
        if(high < low) return -1;
        
        if(high == low) return low;
        
        int mid = (high+low)/2;
        
        if(high > mid && nums[mid] > nums[mid+1])
            return mid;
        else if(low < mid && nums[mid] < nums[mid-1])
            return mid-1;
        
        if(nums[low] > nums[mid])
            return findPivot(nums, low, mid-1);
        return findPivot(nums, mid+1, high);
    }
    
    private int binarySearch(int[] nums, int low, int high, int target){
        if(high < low) return -1;
        
        int mid = (low+high)/2;
        if(nums[mid] == target) return mid;
        else if(nums[mid] < target) return binarySearch(nums, mid+1, high, target);
        else return binarySearch(nums, low, mid-1, target);
    }
}