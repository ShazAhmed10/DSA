package DynamicProgramming.Blind_180.B_State_Transition.B_Hard.P02_BestTimeToBuyAndSellStockIV;

import java.util.Arrays;

public class B_Memoization {
    public int maxProfit(int k, int[] prices) {
        int[][][] dp = new int[prices.length][2][k+1];
        for(int[][] holdingArr : dp){
            for(int[] kArr : holdingArr){
                Arrays.fill(kArr, -1);
            }
        }
        return helper(prices, 0, 0, k, dp);
    }

    public int helper(int[] prices, int day, int holding, int k, int[][][] dp){
        if(day > prices.length - 1 || k <= 0){
            return 0;
        }

        if(dp[day][holding][k] != -1){
            return dp[day][holding][k];
        }

        int pick = 0;
        int notPick = 0;
        if(holding == 1){
            pick = helper(prices, day+1, 0, k-1, dp) + prices[day];
            notPick = helper(prices, day+1, 1, k, dp);
        }
        else{
            pick = helper(prices, day+1, 1, k, dp) - prices[day];
            notPick = helper(prices, day+1, 0, k, dp);
        }

        return dp[day][holding][k] = Math.max(pick, notPick);
    }

    public static void main(String[] args) {
        B_Memoization solution = new B_Memoization();
        System.out.println(solution.maxProfit(2, new int[]{2, 4, 1}) + " (expected 2)");
        System.out.println(solution.maxProfit(2, new int[]{3, 2, 6, 5, 0, 3}) + " (expected 7)");
        System.out.println(solution.maxProfit(2, new int[]{2, 1, 4, 5, 2, 9, 7}) + " (expected 11)");
    }
}
