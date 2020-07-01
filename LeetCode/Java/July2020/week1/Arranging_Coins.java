public class Arranging_Coins {
    /**
     * 
     * There is a binary search method to this that is also
     * accepted, it essentially is normal binary, but
     * if middle equals current, found by taking mid * (mid+1)/2 then we return it's 
     * (int) value else if n < curr right moves to mid -1
     * else left moves to k + 1 then finally we return right as an int
     */
    public int arrangeCoins(int n) {
        return (int) (Math.sqrt(2 * (long)n +0.25 ) - 0.5);
    }
}