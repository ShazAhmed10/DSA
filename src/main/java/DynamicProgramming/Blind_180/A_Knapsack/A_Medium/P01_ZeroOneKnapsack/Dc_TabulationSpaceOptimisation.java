package DynamicProgramming.Blind_180.A_Knapsack.A_Medium.P01_ZeroOneKnapsack;

public class Dc_TabulationSpaceOptimisation {
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

    public static void main(String[] args) {
        Dc_TabulationSpaceOptimisation solution = new Dc_TabulationSpaceOptimisation();
        System.out.println(solution.knapsack01(new int[]{10, 20, 30}, new int[]{60, 100, 120}, 3, 50) + " (expected 220)");
        System.out.println(solution.knapsack01(new int[]{5, 4, 6, 3}, new int[]{10, 40, 30, 50}, 4, 10) + " (expected 90)");
    }
}
