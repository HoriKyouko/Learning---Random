/**
 * Completed with help from Nick Whites video with a 
 * better explanation than the solutions.
 * 
 * Video: https://www.youtube.com/watch?v=AmlVSNBHzJg
 * 
 */
class Solution {
    public int subarraySum(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0,1);
        int sum = 0;
        int output = 0;
        
        for(int i: nums){
            sum += i;
            if(map.containsKey(sum-k))
                output += map.get(sum-k);
            
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        
        return output;
    }
}