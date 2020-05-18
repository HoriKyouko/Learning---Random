class Solution {
    public boolean isHappy(int n) {
        HashSet<Integer> dups = new HashSet<>();
        
        while(!dups.contains(n)){
            if(n == 1)
                return true;
            dups.add(n);
            LinkedList<Integer> digits = new LinkedList<>();
            int number = n;
            while(number > 0){
                digits.push(number % 10);
                number /= 10;
            }
            n = 0;
            while(!digits.isEmpty()){
                n +=  Math.pow(digits.pop(), 2);
            }
        }
        return false;
    }
}