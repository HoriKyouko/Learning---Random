public class XOR_Operation_In_An_Array {
    public int xorOperation(int n, int start) {
        int[] nums = new int[n];
        int output = 0;
        for(int i = 0; i < n; i++){
            nums[i] = start + 2*i;
            output ^= nums[i];
        }
        return output;
    }
}