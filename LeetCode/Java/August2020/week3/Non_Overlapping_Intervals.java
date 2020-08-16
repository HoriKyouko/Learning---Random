public class Non_Overlapping_Intervals{
    public int eraseOverlapIntervals(int[][] intervals) {
        int output = 0;
        
        Arrays.sort(intervals, (a, b) -> a[1] == b[1] ? b[0]-a[0] : a[1]-b[1]);
        
        int prevEnd = Integer.MIN_VALUE;
        
        for(int[] i: intervals){
            if(prevEnd > i[0])
                output++;
            else
                prevEnd = i[1];
        }
        
        return output;
    }
}