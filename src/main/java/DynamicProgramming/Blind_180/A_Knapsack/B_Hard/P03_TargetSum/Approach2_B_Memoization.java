package DynamicProgramming.Blind_180.A_Knapsack.B_Hard.P03_TargetSum;

import java.util.*;

public class Approach2_B_Memoization {
    public int findTargetSumWays(int[] nums, int target) {
        int n = nums.length;
        int total = 0;
        for(int i=0; i<n; i++){
            total += nums[i];
        }

        int redEqSum = total + target;
        if(redEqSum < 0 || (redEqSum % 2) != 0){
            return 0;
        }

        int[][] dp = new int[n+1][redEqSum/2 + 1];
        for(int[] arr : dp){
            Arrays.fill(arr, -1);
        }

        return helper(nums, n, redEqSum/2, dp);
    }

    public int helper(int[] nums, int n, int p, int[][] dp){
        if(n == 0){
            return dp[n][p] = (p == 0) ? 1 : 0;
        }

        if(dp[n][p] != -1){
            return dp[n][p];
        }

        int pick = 0;
        if(nums[n-1] <= p){
            pick = helper(nums, n-1, p - nums[n-1], dp);
        }
        int notPick = helper(nums, n-1, p, dp);

        return dp[n][p] = pick + notPick;
    }

    public static void main(String[] args) {
        Approach2_B_Memoization solution = new Approach2_B_Memoization();
        System.out.println(solution.findTargetSumWays(new int[]{1, 1, 1, 1, 1}, 3) + " (expected 5)");
        System.out.println(solution.findTargetSumWays(new int[]{1}, 1) + " (expected 1)");
        System.out.println(solution.findTargetSumWays(new int[]{0, 0}, 0) + " (expected 4)");
        System.out.println(solution.findTargetSumWays(new int[]{1, 1, 1}, 2) + " (expected 0)");
    }
}
