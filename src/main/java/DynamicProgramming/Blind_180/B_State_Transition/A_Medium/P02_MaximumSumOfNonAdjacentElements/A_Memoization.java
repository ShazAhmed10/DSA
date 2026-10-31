package DynamicProgramming.Blind_180.B_State_Transition.A_Medium.P02_MaximumSumOfNonAdjacentElements;

import java.util.*;

public class A_Memoization {
    public int nonAdjacent(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n+1];
        Arrays.fill(dp, -1);
        helper(n, nums, dp);
        return dp[n];
    }

    public int helper(int n, int[] nums, int[] dp){
        if(n == 0){
            return dp[n] = 0;
        }
        if(n < 0){
            return 0;
        }

        if(dp[n] != -1){
            return dp[n];
        }

        int pick = nums[n-1] + helper(n-2, nums, dp);
        int not_pick = helper(n-1, nums, dp);

        return dp[n] = Math.max(pick, not_pick);
    }

    public static void main(String[] args) {
        A_Memoization solution = new A_Memoization();
        System.out.println(solution.nonAdjacent(new int[]{1, 2, 4}) + " (expected 5)");
        System.out.println(solution.nonAdjacent(new int[]{2, 1, 4, 9}) + " (expected 11)");
    }
}
