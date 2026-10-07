package DynamicProgramming.Blind_180.Medium;

public class BestTimeToBuyAndSellStockWithTransactionFee_A_RecursiveNaive {
    public int maxProfit(int[] prices, int fee) {
        int day = 0;
        return helper(prices, fee, day, 0);
    }

    public int helper(int[] prices, int fee, int day, int holding){
        if(day > prices.length - 1){
            return 0;
        }

        int pick = 0;
        int not_pick = 0;
        if(holding == 1){
            pick = helper(prices, fee, day+1, 0) + prices[day] - fee;
            not_pick = helper(prices, fee, day+1, 1);
        }
        else{
            pick = helper(prices, fee, day+1, 1) - prices[day];
            not_pick = helper(prices, fee, day+1, 0);
        }

        return Math.max(pick, not_pick);
    }
}
