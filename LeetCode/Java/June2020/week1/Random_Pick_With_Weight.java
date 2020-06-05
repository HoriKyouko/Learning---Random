public class Random_Pick_With_Weight{
    int [] indexRange;
    Random rand;
    public Solution(int[] w) {
        for(int i = 1; i < w.length; i++)
            w[i] += w[i-1];

        indexRange = w;
        rand = new Random();
    }
    
    public int pickIndex() {
        int left = 0;
        int right = indexRange.length-1;
        double index = rand.nextInt(indexRange[right]) + 1;
        
        while(left < right){
            int mid = left + (right - left) / 2;
            if(index > indexRange[mid])
                left = mid + 1;
            else
                right = mid;
        }
        
        return left;
    }
}