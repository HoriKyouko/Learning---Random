class Solution {
    /**
        NOTE:
        Not actually a leetcode problem, but was one posted as a Microsoft Interview question.
        Was actually a lot simpler then it first appears when you realize you can just put it
        all into a HashSet and check if the negate is there. Since negating a negative would
        make it positive and if the postive exists then you just need to take the max of 0 or
        what you found.

        I made it return 0 if arr length is zero, but no idea if that would even be a valid input
        but covering my bases :)   
     */
    public int largestInteger(int [] arr) {
        if(arr.length == 0)
			return 0;

		Set<Integer> nums = new HashSet<>();
		int max = 0;
		
		for(int i = 0; i < arr.length; i++) {
			nums.add(arr[i]);
			if(nums.contains(-arr[i]))
				max = Math.max(max, Math.abs(arr[i]));
        }
		
		return max;
    }
}