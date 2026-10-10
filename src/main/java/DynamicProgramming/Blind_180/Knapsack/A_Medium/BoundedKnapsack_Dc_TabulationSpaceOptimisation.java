package DynamicProgramming.Blind_180.Knapsack.A_Medium;

public class BoundedKnapsack_Dc_TabulationSpaceOptimisation {
    public int knapsack01(int[] wt, int[] val, int n, int W) {
        int[] dp = new int[W+1];

        for(int i=1; i<=n; i++){
            for(int j=W; j >= wt[i-1]; j--){
                int pick = val[i-1] + dp[j-wt[i-1]];
                int notPick = dp[j];

                dp[j] = Math.max(pick, notPick);
            }
        }

        return dp[W];
    }
}
