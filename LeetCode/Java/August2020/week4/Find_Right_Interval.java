public class Find_Right_Interval {
    public int[] findRightInterval(int[][] intervals) {
        int[] output = new int[intervals.length];
        /*Arrays.fill(output, -1);
        for(int i = 0; i < intervals.length; i++){
            boolean flag = false;
            for(int j = 0; j < intervals.length; j++){
                if(intervals[i][1] == intervals[j][0]){
                    flag = true;
                    output[i] = j;
                    break;
                }
            }
            if(!flag){
                for(int j = 0; j < intervals.length; j++){
                    if(intervals[i][1] < intervals[j][0]){
                        output[i] = j;
                        break;
                    }
                        
                }
            }
        }
        */
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for(int i = 0; i < intervals.length; i++){
            map.put(intervals[i][0], i);
        }
        
        for(int i = 0; i < intervals.length; i++){
            Integer key = map.ceilingKey(intervals[i][1]);
            output[i] = (key == null) ? -1 : map.get(key);
        }
        return output;
    }
}