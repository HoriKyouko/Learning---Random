public class Power_Of_Four {
    public boolean isPowerOfFour(int num) {
        int biggestPowerOfFour = 1073741824;
        if(num > biggestPowerOfFour)
            return false;
        int sum = 1;
        while (sum <= num){
            if(sum == num)
                return true;
            sum <<= 2;
        }
        return false;
    }
}