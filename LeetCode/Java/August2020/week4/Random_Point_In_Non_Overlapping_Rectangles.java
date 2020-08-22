public class Random_Point_In_Non_Overlapping_Rectangles{
    TreeMap<Integer, Integer> map;
    int[][] arrays;
    int area;
    Random rnd = new Random();
    public Solution(int[][] rects) {
        arrays = rects;
        map = new TreeMap<>();
        area = 0;
        
        for(int i = 0; i < rects.length; i++){
            int[] rect = rects[i];
            area += (rect[2] - rect[0] + 1) * (rect[3] - rect[1] + 1);
            map.put(area, i);
        }
    }
    
    public int[] pick() {
        int c = map.ceilingKey(rnd.nextInt(area) + 1);
        return pickInRect(arrays[map.get(c)]);
    }
    
    private int[] pickInRect(int[] rect){
        int left = rect[0];
        int right = rect[2];
        int bot = rect[1];
        int top = rect[3];
        
        return new int[] {left + rnd.nextInt(right-left+1), bot + rnd.nextInt(top-bot + 1)};
    }
}