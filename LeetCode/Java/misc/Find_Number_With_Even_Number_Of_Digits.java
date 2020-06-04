public class Find_Number_With_Even_Number_Of_Digits {
    public int findNumbers(int[] nums) {
        int output = 0;
        for(int num : nums)
            if(num >= 10 && num < 100 || num >= 1000 && num < 10000)
                output++;
        
        return output;
    }
}