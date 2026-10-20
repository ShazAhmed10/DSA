package DynamicProgramming.Blind_180.Knapsack.A_Medium;

public class CoinChangeII_C_Tabulation {
    public int change(int amount, int[] coins) {
        int n = coins.length;
        int[][] dp = new int[n+1][amount+1];

        for(int i=0; i<=n; i++){
            dp[i][0] = 1;
        }

        for(int i=1; i<=n; i++){
            for(int j=1; j<=amount; j++){
                int pick = 0;
                if(coins[i-1] <= j){
                    pick = dp[i][j - coins[i-1]];
                }
                int notPick = dp[i-1][j];

                dp[i][j] = pick + notPick;
            }
        }

        return dp[n][amount];
    }
}
