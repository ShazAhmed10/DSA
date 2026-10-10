package DynamicProgramming.Blind_180.Knapsack.A_Medium;

public class BoundedKnapsack_C_Tabulation {
    public int knapsack01(int[] wt, int[] val, int n, int W) {
        int[][] dp = new int[n+1][W+1];

        for(int i=1; i<=n; i++){
            for(int j=1; j<=W; j++){
                int pick = 0;
                int notPick = 0;
                if(wt[i-1] <= j){
                    pick = val[i-1] + dp[i-1][j-wt[i-1]];
                }
                notPick = dp[i-1][j];
                dp[i][j] = Math.max(pick, notPick);
            }
        }
        return dp[n][W];
    }
}
