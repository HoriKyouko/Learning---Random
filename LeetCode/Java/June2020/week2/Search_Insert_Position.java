public class Search_Insert_Position {
    public int searchInsert(int[] nums, int target) {
        int lo = 0, hi = nums.length-1, mid;
        while(lo <= hi){
            mid = lo + (hi - lo)/2;
            if(nums[mid] > target) hi = mid-1;
            else if(nums[mid] < target) lo = mid+1;
            else return mid;
        }
        return lo;
    }
}