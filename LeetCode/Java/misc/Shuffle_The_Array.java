public class Shuffle_The_Array {
    public int[] shuffle(int[] nums, int n) {
        int[] output = new int[2*n];
        int j = 0;
        for(int i = 0; i < nums.length; i+=2){
            output[i] = nums[j++];
            output[i+1] = nums[n++];
        }
        return output;
    }
}