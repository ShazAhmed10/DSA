package DynamicProgramming.Blind_180.A_Knapsack.A_Medium.P01_ZeroOneKnapsack;

import java.util.Arrays;

public class B_Memoization {
    public int knapsack01(int[] wt, int[] val, int n, int W) {
        int[][] dp = new int[n+1][W+1];
        for(int[] arr : dp){
            Arrays.fill(arr, -1);
        }
        return helper(wt, val, n, W, dp) ;
    }

    public int helper(int[] wt, int[] val, int n, int w, int[][] dp){
        if(n == 0 || w == 0){
            return 0;
        }

        if(dp[n][w] != -1){
            return dp[n][w];
        }

        int pick = 0;
        if(wt[n-1] <= w){
            pick = val[n-1] + helper(wt, val, n-1, w - wt[n-1], dp);
        }
        int notPick = helper(wt, val, n-1, w, dp);

        return dp[n][w] = Math.max(pick, notPick);
    }

    public static void main(String[] args) {
        B_Memoization solution = new B_Memoization();
        System.out.println(solution.knapsack01(new int[]{10, 20, 30}, new int[]{60, 100, 120}, 3, 50) + " (expected 220)");
        System.out.println(solution.knapsack01(new int[]{5, 4, 6, 3}, new int[]{10, 40, 30, 50}, 4, 10) + " (expected 90)");
    }
}
