public class Reverse_Bits {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        int rev = 0;
        for(int i = 0; i < 32; i++){
            // Assigns rev itself after being bit shifted to the left once.
            rev <<= 1;
            
            if((int)(n & 1) == 1){
                // Takes the value of rev and assigns it whatever teh XOR of rev and bit-value of 1 is.
                rev ^= 1;
            }
            // Assigning n whatever value it is after bitshifting to the right once
            n >>= 1;
        }
        return rev;
    }
}