package DynamicProgramming.Blind_180.State_Transition.B_Hard;

public class BestTimeToBuyAndSellStockFour_C_Tabulation {
    public int maxProfit(int k, int[] prices) {
        int[][][] dp = new int[prices.length + 1][2][k+1];

        for(int day=prices.length-1; day>=0; day--){
            for(int holding=0; holding<2; holding++){
                for(int transaction=1; transaction<=k; transaction++){
                    int pick = 0;
                    int notPick = 0;
                    if(holding == 1){
                        pick = dp[day+1][0][transaction-1] + prices[day];
                        notPick = dp[day+1][1][transaction];
                    }
                    else{
                        pick = dp[day+1][1][transaction] - prices[day];
                        notPick = dp[day+1][0][transaction];
                    }
                    dp[day][holding][transaction] = Math.max(pick, notPick);
                }
            }
        }

        return dp[0][0][k];
    }
}
