package DynamicProgramming.Blind_180.A_Knapsack.A_Medium.P01_ZeroOneKnapsack;

public class Db_TabulationSpaceOptimisation {
    public int knapsack01(int[] wt, int[] val, int n, int W) {
        int[] dp = new int[W+1];

        for(int i=1; i<=n; i++){
            for(int j=W; j>=1; j--){
                int pick = 0;
                int notPick = 0;
                if(wt[i-1] <= j){
                    pick = val[i-1] + dp[j-wt[i-1]];
                }
                notPick = dp[j];
                dp[j] = Math.max(pick, notPick);
            }
        }

        return dp[W];
    }

    public static void main(String[] args) {
        Db_TabulationSpaceOptimisation solution = new Db_TabulationSpaceOptimisation();
        System.out.println(solution.knapsack01(new int[]{10, 20, 30}, new int[]{60, 100, 120}, 3, 50) + " (expected 220)");
        System.out.println(solution.knapsack01(new int[]{5, 4, 6, 3}, new int[]{10, 40, 30, 50}, 4, 10) + " (expected 90)");
    }
}
