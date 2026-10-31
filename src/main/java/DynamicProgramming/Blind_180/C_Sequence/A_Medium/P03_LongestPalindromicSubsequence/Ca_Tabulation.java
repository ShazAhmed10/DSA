package DynamicProgramming.Blind_180.C_Sequence.A_Medium.P03_LongestPalindromicSubsequence;

public class Ca_Tabulation {
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

    public static void main(String[] args) {
        Ca_Tabulation solution = new Ca_Tabulation();
        System.out.println(solution.longestPalindromeSubseq("bbbab") + " (expected 4)");
        System.out.println(solution.longestPalindromeSubseq("cbbd") + " (expected 2)");
        System.out.println(solution.longestPalindromeSubseq("a") + " (expected 1)");
    }
}
