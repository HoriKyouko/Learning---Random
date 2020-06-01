/**
 * Completed with help from Nick Whites video with a 
 * better explanation than the solutions.
 * 
 * Video: https://www.youtube.com/watch?v=qKczfGUrFY4
 * 
 */

class Solution {
    public int[][] merge(int[][] intervals) {
        if(intervals.length <= 1){
            return intervals;
        }
        
        Arrays.sort(intervals, (arr1, arr2) -> Integer.compare(arr1[0], arr2[0]));
        
        List<int[]> output = new ArrayList();
        int [] currentInterval = intervals[0];
        output.add(currentInterval);
        
        for(int [] interval : intervals){
            int currentStart = currentInterval[0];
            int currentEnd = currentInterval[1];
            
            if(currentEnd >= interval[0]){
                currentInterval[1] = Math.max(currentEnd, interval[1]);
            }
            else{
                currentInterval = interval;
                output.add(currentInterval);
            }
        }
        
        return output.toArray(new int[output.size()][]);
    }
}