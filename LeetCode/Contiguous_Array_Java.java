/**
 * Completed with help from Nick Whites video with a 
 * better explanation than the solutions.
 * 
 * Video: https://www.youtube.com/watch?v=nSEO5zOwP7g
 * 
 */
class Solution {
    public int findMaxLength(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        
        int max = 0;
        int count = 0;
        
        for(int i = 0; i < nums.length; i++){
            //count += (nums[i] == 0) ? -1 : 1;
            if(nums[i] == 0){
                count -= 1;
            }
            else{
                count += 1;
            }
            
            if(map.containsKey(count)){
                max = Math.max(max, i-map.get(count));
            }
            else{
                map.put(count, i);
            }
        }
        return max;
    }
}