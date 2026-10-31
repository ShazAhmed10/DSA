package DynamicProgramming.Blind_180.A_Knapsack.A_Medium.P02_CoinChangeII;

import java.util.Arrays;

public class B_Memoization {
    public int change(int amount, int[] coins) {
        int n = coins.length;
        int[][] dp = new int[n+1][amount+1];
        for(int[] arr : dp){
            Arrays.fill(arr, -1);
        }
        return helper(coins, n, amount, dp);
    }

    public int helper(int[] coins, int n, int targetCapacity, int[][] dp){
        if(targetCapacity == 0){
            return dp[n][targetCapacity] = 1;
        }

        if(n == 0){
            return dp[n][targetCapacity] = 0;
        }

        if(dp[n][targetCapacity] != -1){
            return dp[n][targetCapacity];
        }

        int pick = 0;
        if(coins[n-1] <= targetCapacity){
            pick = helper(coins, n, targetCapacity - coins[n-1], dp);
        }
        int notPick = helper(coins, n-1, targetCapacity, dp);

        return dp[n][targetCapacity] = pick + notPick;
    }

    public static void main(String[] args) {
        B_Memoization solution = new B_Memoization();
        System.out.println(solution.change(5, new int[]{1, 2, 5}) + " (expected 4)");
        System.out.println(solution.change(3, new int[]{2}) + " (expected 0)");
        System.out.println(solution.change(10, new int[]{10}) + " (expected 1)");
    }
}
