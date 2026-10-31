package DynamicProgramming.Blind_180.B_State_Transition.B_Hard.P02_BestTimeToBuyAndSellStockIV;

public class C_Tabulation {
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

    public static void main(String[] args) {
        C_Tabulation solution = new C_Tabulation();
        System.out.println(solution.maxProfit(2, new int[]{2, 4, 1}) + " (expected 2)");
        System.out.println(solution.maxProfit(2, new int[]{3, 2, 6, 5, 0, 3}) + " (expected 7)");
        System.out.println(solution.maxProfit(2, new int[]{2, 1, 4, 5, 2, 9, 7}) + " (expected 11)");
    }
}
