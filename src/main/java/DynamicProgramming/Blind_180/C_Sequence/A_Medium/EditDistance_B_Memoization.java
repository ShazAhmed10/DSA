package DynamicProgramming.Blind_180.C_Sequence.A_Medium;

import java.util.Arrays;

public class EditDistance_B_Memoization {
    public int minDistance(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();
        int[][] dp = new int[n+1][m+1];
        for(int[] arr : dp){
            Arrays.fill(arr, -1);
        }

        return helper(word1, word2, n, m, dp);
    }

    public int helper(String word1, String word2, int n, int m, int[][] dp){
        if(n == 0 && m == 0){
            return dp[n][m] = 0;
        }

        if(n == 0){
            return dp[n][m] = m;
        }

        if(m == 0){
            return dp[n][m] = n;
        }

        if(dp[n][m] != -1){
            return dp[n][m];
        }

        int currDistance = 0;
        char c1 = word1.charAt(n-1);
        char c2 = word2.charAt(m-1);
        if(c1 == c2){
            currDistance = helper(word1, word2, n-1, m-1, dp);
        }
        else{
            int insert = helper(word1, word2, n, m-1, dp);
            int delete = helper(word1, word2, n-1, m, dp);
            int replace = helper(word1, word2, n-1, m-1, dp);

            currDistance = 1 + Math.min(Math.min(insert, delete), replace);
        }

        return dp[n][m] = currDistance;
    }
}
