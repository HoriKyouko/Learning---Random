class Solution {
    public int maxProfit(int[] prices) {
        /*
            Profit starts at 0 and we begin on the second days prices.
            We check if todays price is greater than yesterdays price
            and if it is we "buy" and "sell" and make profit.
            
            Example:
            [1,7,2,3,6]
            
            We would make profit on day 2 if we bought day 1 -> 7-1 = 6
            We don't make profit on day 3 if we bought day 2 -> 2-7 = -5
            We would make profit on day 4 if we bought day 3 -> 3-2 = 1
            We would make profit on day 5 if we bought day 4 -> 6-3 = 3
            
            If we assume negatives are 0 we would then have 6 + 0 + 1 + 3 = 10
            Thus the maximum profit we could make is 10 from the given information.
        */
        int profit = 0;
        for(int i = 1; i < prices.length; i++)
            if(prices[i] > prices[i-1])
                profit += prices[i] - prices[i-1];
                
        return profit;
    }
}