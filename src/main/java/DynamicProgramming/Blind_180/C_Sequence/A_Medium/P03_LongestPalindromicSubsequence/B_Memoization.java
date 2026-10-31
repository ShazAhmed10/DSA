package DynamicProgramming.Blind_180.C_Sequence.A_Medium.P03_LongestPalindromicSubsequence;

import java.util.Arrays;

public class B_Memoization {
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int start = 0;
        int end = n-1;
        int[][] dp = new int[n][n];
        for(int[] arr : dp){
            Arrays.fill(arr, -1);
        }

        return helper(s, start, end, dp);
    }

    public int helper(String s, int start, int end, int[][] dp){
        if(start > end){
            return 0;
        }

        if(start == end){
            return dp[start][end] = 1;
        }

        if(dp[start][end] != -1){
            return dp[start][end];
        }

        char a = s.charAt(start);
        char b = s.charAt(end);
        int currLength = 0;
        if(a == b){
            currLength = 2 + helper(s, start + 1, end - 1, dp);
        }
        else{
            currLength = Math.max(helper(s, start + 1, end, dp), helper(s, start, end - 1, dp));
        }

        return dp[start][end] = currLength;
    }

    public static void main(String[] args) {
        B_Memoization solution = new B_Memoization();
        System.out.println(solution.longestPalindromeSubseq("bbbab") + " (expected 4)");
        System.out.println(solution.longestPalindromeSubseq("cbbd") + " (expected 2)");
        System.out.println(solution.longestPalindromeSubseq("a") + " (expected 1)");
    }
}
