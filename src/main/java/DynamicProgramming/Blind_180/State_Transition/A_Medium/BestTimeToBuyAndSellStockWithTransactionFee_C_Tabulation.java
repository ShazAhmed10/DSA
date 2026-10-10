package DynamicProgramming.Blind_180.State_Transition.A_Medium;

public class BestTimeToBuyAndSellStockWithTransactionFee_C_Tabulation {
    public int maxProfit(int[] prices, int fee) {
        int[][] dp = new int[prices.length + 1][2];

        for(int day=prices.length-1; day>=0; day--){
            for(int holding=0; holding<2; holding++){
                int pick = 0;
                int not_pick = 0;
                if(holding == 1){
                    pick = dp[day+1][0] + prices[day] - fee;
                    not_pick = dp[day+1][1];
                }
                else{
                    pick = dp[day+1][1] - prices[day];
                    not_pick = dp[day+1][0];
                }
                dp[day][holding] = Math.max(pick, not_pick);
            }
        }

        return dp[0][0];
    }
}
