package DynamicProgramming.Blind_180.B_State_Transition.A_Medium.P02_MaximumSumOfNonAdjacentElements;

import java.util.*;

public class B_Tabulation {
    public int nonAdjacent(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n+1];
        Arrays.fill(dp, -1);

        dp[0] = 0;

        for(int i=1; i<=n; i++){
            int pick = nums[i-1];
            if(i > 1){
                pick += dp[i-2];
            }
            int not_pick = dp[i-1];
            dp[i] = Math.max(pick, not_pick);
        }

        return dp[n];
    }

    public static void main(String[] args) {
        B_Tabulation solution = new B_Tabulation();
        System.out.println(solution.nonAdjacent(new int[]{1, 2, 4}) + " (expected 5)");
        System.out.println(solution.nonAdjacent(new int[]{2, 1, 4, 9}) + " (expected 11)");
    }
}
