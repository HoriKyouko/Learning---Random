class Solution {
    public boolean isPerfectSquare(int num) {
        long newton = num;
        while(newton * newton > num)
            newton = (newton + num / newton) / 2;
        
        return newton * newton == num;
    }
}