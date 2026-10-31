package DynamicProgramming.Blind_180.A_Knapsack.B_Hard.P01_PartitionSetIntoTwoSubsetsMinDifference;

import java.util.Arrays;

public class B_Memoization {
    public int minDifference(int[] arr, int n) {
        int total = 0;
        for(int i=0; i<n; i++){
            total += arr[i];
        }

        int[][] dp = new int[n+1][total/2 + 1];
        for(int[] curr : dp){
            Arrays.fill(curr, -1);
        }

        int subset = helper(arr, n, total/2, dp);

        return total - (2 * subset);
    }

    public int helper(int[] arr, int n, int capacity, int[][] dp){
        if(n == 0 || capacity == 0){
            return dp[n][capacity] = 0;
        }

        if(dp[n][capacity] != -1){
            return dp[n][capacity];
        }

        int pick = 0;
        if(arr[n-1] <= capacity){
            pick = arr[n-1] + helper(arr, n-1, capacity - arr[n-1], dp);
        }
        int notPick = helper(arr, n-1, capacity, dp);

        return dp[n][capacity] = Math.max(pick, notPick);
    }

    public static void main(String[] args) {
        B_Memoization solution = new B_Memoization();
        System.out.println(solution.minDifference(new int[]{1, 7, 14, 5}, 4) + " (expected 1)");
        System.out.println(solution.minDifference(new int[]{3, 1, 6, 2, 2}, 5) + " (expected 0)");
        System.out.println(solution.minDifference(new int[]{1}, 1) + " (expected 1)");
    }
}
