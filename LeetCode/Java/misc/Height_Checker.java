public class Height_Checker {
    public int heightChecker(int[] heights) {
        int [] sortedArray = heights.clone();
        Arrays.sort(sortedArray);
        int count = 0;
        for(int i = 0; i < heights.length; i++)
            if(heights[i] != sortedArray[i])
                count++;
            
        return count;
    }
}