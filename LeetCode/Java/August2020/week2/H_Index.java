public class H_Index {
    public int hIndex(int[] citations) {
        int n = citations.length;
        int[] buckets = new int[n+1];
        
        for(int i : citations){
            if(i >= n)
                buckets[n]++;
            else
                buckets[i]++;
        }
        
        int count = 0;
        for(int i = n; i>= 0; i--){
            count += buckets[i];
            if(count >= i)
                return i;
        }
        return 0;
    }
}