public class Minimum_Cost_For_Tickets {
    public int mincostTickets(int[] days, int[] costs) {
        HashSet<Integer> set = new HashSet<>();
        // populate the set with distance between days.
        for(int d : days)
            set.add(d-(days[0]-1));
        // fills our array with max values
        int[] dp = new int[days[days.length-1]];
        Arrays.fill(dp, Integer.MAX_VALUE);
        // find the minimum cost for the first day, could be costs[2] if the first day isn't 1 or 7
        dp[0] = Math.min(costs[0], Math.min(costs[1],costs[2]));
        // loop through every day.
        for(int i = 1; i < dp.length; i++){
            // if we have a day that isn't in our array just give its value the previous value.
            if(!set.contains(i+1)){
                dp[i] = dp[i-1];
            }
            else{
                // first day pass, or previous cost
                dp[i] = Math.min(dp[i], costs[0] + dp[i-1]);
                // seven day pass or previous cost
                if(i >= 7)
                    dp[i] = Math.min(dp[i], costs[1] + dp[i-7]);
                else
                    dp[i] = Math.min(dp[i], costs[1]);
                // 30 day pass or previous cost
                if( i >= 30)
                    dp[i] = Math.min(dp[i], costs[2] + dp[i-30]);
                else
                    dp[i] = Math.min(dp[i], costs[2]);
            }
        }
        // return the last day of the trip.
        return dp[dp.length-1];
    }
}