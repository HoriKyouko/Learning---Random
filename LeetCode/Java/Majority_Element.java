public class Majority_Element {
    public int majorityElement(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        
        for(int i : nums)
            map.put(i, map.getOrDefault(i, 0) + 1);
        
        int output = 0;
        
        for(int i : nums)
            if(map.get(i) > nums.length / 2)
                output = i;
        
        return output;
    }
}