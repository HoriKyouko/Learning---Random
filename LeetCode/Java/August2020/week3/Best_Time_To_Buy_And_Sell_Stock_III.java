public class Best_Time_To_Buy_And_Sell_Stock_III {
    public int maxProfit(int[] prices) {
        int firstBuy = Integer.MAX_VALUE;
        //profit after buy/sell
        int afterFirstSell = 0;
        int afterSecondBuy = Integer.MIN_VALUE;
        int afterSecondSell = 0;
        for (int curPrice : prices) {
            //for first buy price ,the lower,the better
            firstBuy = Math.min(firstBuy, curPrice); 
            // the profit after first sell ,the higher,the better
            afterFirstSell = Math.max(afterFirstSell, curPrice-firstBuy);
            //the profit left after second buy,the higher,the better 
            afterSecondBuy = Math.max(afterSecondBuy, afterFirstSell - curPrice);
            // the profit left after second sell ,the higher,the better
            afterSecondSell = Math.max(afterSecondSell, afterSecondBuy + curPrice); 
        }
        // afterSecondSell will be the max profit ultimately
        return afterSecondSell;
    }
}