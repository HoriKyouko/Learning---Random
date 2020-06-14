public class Cheapest_Flights_Within_K_Stops {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        int[] cost= new int[n];
        Arrays.fill(cost, Integer.MAX_VALUE);
        cost[src] = 0;
        for (int i=0; i<=K; i++){
            int[] tmp = Arrays.copyOf(cost, n);
            for (int[] a: flights){
                int cur = a[0], next=a[1], price=a[2];
                if (cost[cur]== Integer.MAX_VALUE)
                    continue;
                tmp[next] = Math.min(tmp[next], cost[cur]+price);
            }
            cost = tmp;
        }
        return cost[dst] == Integer.MAX_VALUE? -1: cost[dst];
    }
}