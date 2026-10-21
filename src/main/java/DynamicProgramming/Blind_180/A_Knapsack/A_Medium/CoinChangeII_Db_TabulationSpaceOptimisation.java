package DynamicProgramming.Blind_180.A_Knapsack.A_Medium;

public class CoinChangeII_Db_TabulationSpaceOptimisation {
    public int change(int amount, int[] coins) {
        int n = coins.length;
        int[] dp = new int[amount+1];

        dp[0] = 1;
        for(int i=1; i<=n; i++){
            for(int j=1; j<=amount; j++){
                int pick = 0;
                if(coins[i-1] <= j){
                    pick = dp[j - coins[i-1]];
                }
                int notPick = dp[j];

                dp[j] = pick + notPick;
            }
        }

        return dp[amount];
    }
}
