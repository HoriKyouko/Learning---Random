class Solution {
    public int[] countBits(int num) {
        int[] output = new int[num+1];
        output[0] = 0;
        int pow = 1;
        int i = 1;
        int count = 0;
        while(i <= num){
            if(i == pow){
                pow *= 2;
                count = 0;
            }
            output[i] = output[count] + 1;
            i++;
            count++;
        }
        
        return output;
    }
}