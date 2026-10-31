package DynamicProgramming.Blind_180.A_Knapsack.B_Hard.P03_TargetSum;

import java.util.Arrays;

public class Approach1_B_Memoization {
    public int findTargetSumWays(int[] nums, int target) {
        int n = nums.length;

        int totalSum = 0;
        for(int num : nums){
            totalSum += num;
        }

        int[][] dp = new int[n+1][2 * totalSum + 1];
        for(int[] arr : dp){
            Arrays.fill(arr, -1);
        }

        int curr = 0;
        return helper(nums, target, n, curr, dp, totalSum);
    }

    public int helper(int[] nums, int target, int n, int curr, int[][] dp, int totalSum){
        if(n == 0 && curr == target){
            return dp[n][curr + totalSum] = 1;
        }

        if(n == 0){
            return dp[n][curr + totalSum] = 0;
        }

        if(dp[n][curr + totalSum] != -1){
            return dp[n][curr + totalSum];
        }

        int way1 = helper(nums, target, n-1, curr - nums[n-1], dp, totalSum);
        int way2 = helper(nums, target, n-1, curr + nums[n-1], dp, totalSum);

        return dp[n][curr + totalSum] = way1 + way2;
    }

    public static void main(String[] args) {
        Approach1_B_Memoization solution = new Approach1_B_Memoization();
        System.out.println(solution.findTargetSumWays(new int[]{1, 1, 1, 1, 1}, 3) + " (expected 5)");
        System.out.println(solution.findTargetSumWays(new int[]{1}, 1) + " (expected 1)");
        System.out.println(solution.findTargetSumWays(new int[]{0, 0}, 0) + " (expected 4)");
        System.out.println(solution.findTargetSumWays(new int[]{1, 1, 1}, 2) + " (expected 0)");
    }
}
