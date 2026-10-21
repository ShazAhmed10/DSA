package DynamicProgramming.Blind_180.C_Sequence.A_Medium;

import java.util.Arrays;

public class LongestCommonSubsequence_B_Memoization {
    public int longestCommonSubsequence(String text1, String text2) {
        int n = text1.length();
        int m = text2.length();
        int[][] dp = new int[n+1][m+1];

        for(int[] arr : dp){
            Arrays.fill(arr, -1);
        }

        return helper(text1, text2, n, m, dp);
    }

    public int helper(String text1, String text2, int n, int m, int[][] dp){
        if(m == 0 || n == 0){
            return dp[n][m] = 0;
        }

        if(dp[n][m] != -1){
            return dp[n][m];
        }

        char a = text1.charAt(n-1);
        char b = text2.charAt(m-1);
        if(a == b){
            return dp[n][m] = 1 + helper(text1, text2, n-1, m-1, dp);
        }
        else{
            return dp[n][m] = Math.max(helper(text1, text2, n-1, m, dp), helper(text1, text2, n, m-1, dp));
        }
    }
}
