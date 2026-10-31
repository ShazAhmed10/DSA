package DynamicProgramming.Blind_180.B_State_Transition.A_Medium.P04_BestTimeToBuyAndSellStockWithTransactionFee;

import java.util.Arrays;

public class B_Memoization {
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

    public static void main(String[] args) {
        B_Memoization solution = new B_Memoization();
        System.out.println(solution.maxProfit(new int[]{1, 3, 2, 8, 4, 9}, 2) + " (expected 8)");
        System.out.println(solution.maxProfit(new int[]{1, 3, 7, 5, 10, 3}, 3) + " (expected 6)");
        System.out.println(solution.maxProfit(new int[]{1, 5, 4, 9}, 2) + " (expected 6)");
        System.out.println(solution.maxProfit(new int[]{1}, 0) + " (expected 0)");
    }
}
