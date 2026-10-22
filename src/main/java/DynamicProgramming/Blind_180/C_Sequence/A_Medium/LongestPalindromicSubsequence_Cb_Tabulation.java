package DynamicProgramming.Blind_180.C_Sequence.A_Medium;

public class LongestPalindromicSubsequence_Cb_Tabulation {
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];

        for(int diag = 0; diag < n; diag++){
            dp[diag][diag] = 1;
        }

        for(int start = n-1; start >= 0; start--){
            char a = s.charAt(start);
            for(int end = start+1; end < n; end++){
                char b = s.charAt(end);
                if(a == b){
                    dp[start][end] = 2 + dp[start + 1][end - 1];
                }
                else{
                    dp[start][end] = Math.max(dp[start + 1][end], dp[start][end - 1]);
                }
            }
        }

        return dp[0][n-1];
    }
}
