package DynamicProgramming.Blind_180.D_Interval.B_Hard.P01_MatrixChainMultiplication;

import java.util.Arrays;

public class B_Memoization {
    public int matrixMultiplication(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n][n];
        for(int[] arr : dp){
            Arrays.fill(arr, -1);
        }

        return helper(nums, 0, n-1, dp);
    }

    public int helper(int[] nums, int left, int right, int[][] dp){
        if(left+1 == right){
            return dp[left][right] = 0;
        }

        if(dp[left][right] != -1){
            return dp[left][right];
        }

        int currMinMultiplyCost = Integer.MAX_VALUE;
        for(int i=left+1; i<right; i++){
            int currLeftCost = helper(nums, left, i, dp);
            int currRightCost = helper(nums, i, right, dp);
            int currCost = nums[i] * nums[left] * nums[right];
            int currTotalCost = currLeftCost + currRightCost + currCost;

            currMinMultiplyCost = Math.min(currTotalCost, currMinMultiplyCost);
        }

        return dp[left][right] = currMinMultiplyCost;
    }

    public static void main(String[] args) {
        B_Memoization solution = new B_Memoization();
        System.out.println(solution.matrixMultiplication(new int[]{10, 15, 20, 25}) + " (expected 8000)");
        System.out.println(solution.matrixMultiplication(new int[]{4, 2, 3}) + " (expected 24)");
        System.out.println(solution.matrixMultiplication(new int[]{10, 15, 20, 25, 30}) + " (expected 15500)");
        System.out.println(solution.matrixMultiplication(new int[]{5, 10}) + " (expected 0)");
    }
}
