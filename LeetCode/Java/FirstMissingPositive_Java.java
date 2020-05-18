class Solution {
    public int firstMissingPositive(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        
        for(int i: nums)
            set.add(i);
        
        int counter = 1;
        while(set.contains(counter))
            counter++;
        
        return counter;
    }
}