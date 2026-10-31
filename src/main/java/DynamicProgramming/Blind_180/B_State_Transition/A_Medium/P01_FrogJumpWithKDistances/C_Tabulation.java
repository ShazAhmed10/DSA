package DynamicProgramming.Blind_180.B_State_Transition.A_Medium.P01_FrogJumpWithKDistances;

import java.util.*;

public class C_Tabulation {
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

    public static void main(String[] args) {
        C_Tabulation solution = new C_Tabulation();
        System.out.println(solution.frogJump(new int[]{10, 5, 20, 0, 15}, 2) + " (expected 15)");
        System.out.println(solution.frogJump(new int[]{15, 4, 1, 14, 15}, 3) + " (expected 2)");
    }
}
