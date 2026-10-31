package DynamicProgramming.Blind_180.C_Sequence.A_Medium.P01_LongestCommonSubsequence;

public class C_Tabulation {
    public int longestCommonSubsequence(String text1, String text2) {
        int n = text1.length();
        int m = text2.length();
        int[][] dp = new int[n+1][m+1];

        for(int i=1; i<=n; i++){
            char a = text1.charAt(i-1);
            for(int j=1; j<=m; j++){
                char b = text2.charAt(j-1);
                if(a == b){
                    dp[i][j] = 1 + dp[i-1][j-1];
                }
                else{
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }

        return dp[n][m];
    }

    public static void main(String[] args) {
        C_Tabulation solution = new C_Tabulation();
        System.out.println(solution.longestCommonSubsequence("abcde", "ace") + " (expected 3)");
        System.out.println(solution.longestCommonSubsequence("abc", "abc") + " (expected 3)");
        System.out.println(solution.longestCommonSubsequence("abc", "def") + " (expected 0)");
        System.out.println(solution.longestCommonSubsequence("ab", "bb") + " (expected 1)");
    }
}
