public class Power_Of_2 {
    public boolean isPowerOfTwo(int n) {
        if(n <= 0)
            return false;
        
        /*
            ORIGINAL SOLUTION WITH O(N) runtime and space complexity

        String str = Integer.toBinaryString(n);
        int one = 0;
        for(char c: str.toCharArray())
            if(c == '1')
                one++;
                
        return (one == 1) ? true : false;*/

        /* O(1) Space and time complexity solution using Bit Shifting.
            Works as so:
            n = 8
            8 = 0.....1000 in binary
            -8 = 1....1000 in binary

            when we & the bits together we get 0 for 0&0, 1&0 and 0&1 and only 1 for 1&1
            so if they are a power of 2 then there should be no bit change when & hence
            the check if it == to the original.

            n = 7
            7 = 0.....0111 in binary
            -7 = 1....1001 in binary

            This would yield a 1 when we & and 1 != 7 so it is not a power of two.
        */
        return (n&(-n)) == n;
    }
}