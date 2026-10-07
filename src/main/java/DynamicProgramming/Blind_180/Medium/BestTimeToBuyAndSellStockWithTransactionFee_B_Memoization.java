package DynamicProgramming.Blind_180.Medium;

import java.util.Arrays;

public class BestTimeToBuyAndSellStockWithTransactionFee_B_Memoization {
    public int maxProfit(int[] prices, int fee) {
        int day = 0;
        int[][] dp = new int[prices.length][2];
        for(int[] arr : dp){
            Arrays.fill(arr, -1);
        }
        return helper(prices, fee, day, 0, dp);
    }

    public int helper(int[] prices, int fee, int day, int holding, int[][] dp){
        if(day > prices.length - 1){
            return 0;
        }

        if(dp[day][holding] != -1){
            return dp[day][holding];
        }

        int pick = 0;
        int not_pick = 0;
        if(holding == 1){
            pick = helper(prices, fee, day+1, 0, dp) + prices[day] - fee;
            not_pick = helper(prices, fee, day+1, 1, dp);
        }
        else{
            pick = helper(prices, fee, day+1, 1, dp) - prices[day];
            not_pick = helper(prices, fee, day+1, 0, dp);
        }

        return dp[day][holding] = Math.max(pick, not_pick);
    }
}
