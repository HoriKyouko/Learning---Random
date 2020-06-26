public class Decompress_Run_Length_Encoded_List {
    public int[] decompressRLElist(int[] nums) {
        List<Integer> output = new ArrayList<>();
        
        for(int i = 0; i < nums.length; i+=2){
            int freq = nums[i];
            int val = nums[i+1];
            for(int j = 0; j < freq; j++)
                output.add(val);
        }
        
        return output.stream().mapToInt(i->i).toArray();
    }
}