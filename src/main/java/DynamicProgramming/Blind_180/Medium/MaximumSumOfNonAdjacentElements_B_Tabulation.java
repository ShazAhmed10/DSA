package DynamicProgramming.Blind_180.Medium;

import java.util.*;

public class MaximumSumOfNonAdjacentElements_B_Tabulation {
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
}
