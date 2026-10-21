package DynamicProgramming.Blind_180.C_Sequence.A_Medium;

import java.util.Arrays;

public class LongestCommonSubstring_B_Memoization {
    public int longestCommonSubstr(String str1, String str2) {
        int n = str1.length();
        int m = str2.length();
        int best = 0;
        int[][] dp = new int[n+1][m+1];
        for(int[] arr : dp){
            Arrays.fill(arr, -1);
        }

        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                best = Math.max(best, helper(str1, str2, i, j, dp));
            }
        }

        return best;
    }

    public int helper(String str1, String str2, int i, int j, int[][] dp){
        if(i >= str1.length() || j >= str2.length()){
            return dp[i][j] = 0;
        }

        if(dp[i][j] != -1){
            return dp[i][j];
        }

        char a = str1.charAt(i);
        char b = str2.charAt(j);

        int length = 0;
        if(a == b){
            length = 1 + helper(str1, str2, i+1, j+1, dp);
        }

        return dp[i][j] = length;
    }
}
