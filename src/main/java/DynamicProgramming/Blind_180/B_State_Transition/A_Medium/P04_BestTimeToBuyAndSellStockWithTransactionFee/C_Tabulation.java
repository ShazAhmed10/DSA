package DynamicProgramming.Blind_180.B_State_Transition.A_Medium.P04_BestTimeToBuyAndSellStockWithTransactionFee;

public class C_Tabulation {
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

    public static void main(String[] args) {
        C_Tabulation solution = new C_Tabulation();
        System.out.println(solution.maxProfit(new int[]{1, 3, 2, 8, 4, 9}, 2) + " (expected 8)");
        System.out.println(solution.maxProfit(new int[]{1, 3, 7, 5, 10, 3}, 3) + " (expected 6)");
        System.out.println(solution.maxProfit(new int[]{1, 5, 4, 9}, 2) + " (expected 6)");
        System.out.println(solution.maxProfit(new int[]{1}, 0) + " (expected 0)");
    }
}
