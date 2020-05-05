/**
 * At the time of submitting this I know there is a bit version
 * but didn't want to slap it down and say I knew it when I didn't.
 * This comment it to remind me that their is a more efficent solution
 * and I should come back to this and try and find it. 
 */
class Solution {
    public int findComplement(int num) {
        if(num == 0) return 1;
        StringBuilder sb = new StringBuilder();
        while(num != 0){
            if(num % 2 == 1)
                sb.append(0);
            else
                sb.append(1);
            
            num /= 2;
        }
        sb.reverse();
        return Integer.parseInt(sb.toString(), 2);
    }
}