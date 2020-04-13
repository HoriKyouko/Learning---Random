class Solution {
    public int lastStoneWeight(int[] stones) {
        if(stones.length == 1) return stones[0];
        Arrays.sort(stones);
        int len = stones.length;
        while(true){
            int y = stones[len-1];
            int x = stones[len-2];
            if(x == 0) return y;
            
            if(x == y) x = y = 0;
            else {
                y -= x;
                x = 0;
            }
            stones[len-1] = y;
            stones[len-2] = x;
            Arrays.sort(stones);
        }
    }
}