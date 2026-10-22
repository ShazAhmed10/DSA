package DynamicProgramming.Blind_180.C_Sequence.A_Medium;

public class LongestPalindromicSubsequence_Ca_Tabulation {
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] dp = new int[n+1][n+1];

        for(int start = n-1; start >= 0; start--){
            char a = s.charAt(start);
            for(int end = 0; end < n; end++){
                if(start > end){
                    continue;
                }

                if(start == end){
                    dp[start][end] = 1;
                    continue;
                }

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
