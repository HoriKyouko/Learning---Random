class Solution{
    public int[][] kClosest(int[][] points, int K) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparing(a -> -a[0] * a[0] - a[1] * a[1]));
        
        for(int[] p: points){
            pq.add(p);
            if(pq.size() > K) pq.poll();
        }
        
        return pq.toArray(new int[K][2]);
    }
}