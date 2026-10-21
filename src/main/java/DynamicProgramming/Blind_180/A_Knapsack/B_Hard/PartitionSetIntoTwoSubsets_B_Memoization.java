package DynamicProgramming.Blind_180.A_Knapsack.B_Hard;

import java.util.Arrays;

public class PartitionSetIntoTwoSubsets_B_Memoization {
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
}
