package DynamicProgramming.Easy;

import java.util.*;

public class FrogJumpWithKDistances_C_Tabulation {
    public int frogJump(int[] heights, int k) {
        int n = heights.length;
        int[] dp = new int[n+1];
        Arrays.fill(dp, Integer.MAX_VALUE);

        dp[1] = 0;

        for(int i=2; i<=n; i++){
            for(int j=1; j<=k; j++){
                if(i > j){
                    dp[i] = Math.min(dp[i], dp[i-j] + Math.abs(heights[i-1] - heights[i-1-j]));
                }
                else {
                    break;
                }
            }
        }

        return dp[n];
    }
}
