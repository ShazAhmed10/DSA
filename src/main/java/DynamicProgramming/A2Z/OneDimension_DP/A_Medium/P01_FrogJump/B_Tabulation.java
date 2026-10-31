package DynamicProgramming.A2Z.OneDimension_DP.A_Medium.P01_FrogJump;

import java.util.*;

public class B_Tabulation {
    public int frogJump(int[] heights) {
        int n = heights.length;
        int[] dp = new int[n+1];
        Arrays.fill(dp, 0);
        dp[1] = 0;

        for(int i=2; i<=n; i++){
            int way_1 = dp[i-1] + Math.abs(heights[i-1] - heights[i-2]);
            int way_2 = Integer.MAX_VALUE;
            if(i > 2){
                way_2 = dp[i-2] + Math.abs(heights[i-1] - heights[i-3]);
            }
            dp[i] = Math.min(way_1, way_2);
        }

        return dp[n];
    }

    public static void main(String[] args) {
        B_Tabulation solution = new B_Tabulation();
        System.out.println(solution.frogJump(new int[]{2, 1, 3, 5, 4}) + " (expected 2)");
        System.out.println(solution.frogJump(new int[]{7, 5, 1, 2, 6}) + " (expected 9)");
    }
}
