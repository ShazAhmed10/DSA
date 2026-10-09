package DynamicProgramming.Blind_180.Medium;

public class BestTimeToBuyAndSellStockFour_A_RecursiveNaive {
    public int maxProfit(int k, int[] prices) {
        int day = 0;
        return helper(prices, day, 0, k);
    }

    public int helper(int[] prices, int day, int holding, int k){
        if(day > prices.length - 1 || k == 0){
            return 0;
        }

        int pick = 0;
        int notPick = 0;
        if(holding == 1){
            pick = helper(prices, day+1, 0, k-1) + prices[day];
            notPick = helper(prices, day+1, 1, k);
        }
        else{
            pick = helper(prices, day+1, 1, k) - prices[day];
            notPick = helper(prices, day+1, 0, k);
        }

        return Math.max(pick, notPick);
    }
}