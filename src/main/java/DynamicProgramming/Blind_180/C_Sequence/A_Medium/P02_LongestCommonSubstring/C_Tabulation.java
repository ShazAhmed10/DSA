package DynamicProgramming.Blind_180.C_Sequence.A_Medium.P02_LongestCommonSubstring;

public class C_Tabulation {
    public int longestCommonSubstr(String str1, String str2) {
        int n = str1.length();
        int m = str2.length();
        int best = 0;
        int[][] dp = new int[n+1][m+1];

        for(int i=n-1; i>=0; i--){
            char a = str1.charAt(i);
            for(int j=m-1; j>=0; j--){
                char b = str2.charAt(j);
                if(a == b){
                    dp[i][j] = 1 + dp[i+1][j+1];
                }

                best = Math.max(best, dp[i][j]);
            }
        }

        return best;
    }

    public static void main(String[] args) {
        C_Tabulation solution = new C_Tabulation();
        System.out.println(solution.longestCommonSubstr("abcde", "abfce") + " (expected 2)");
        System.out.println(solution.longestCommonSubstr("abcdxyz", "xyzabcd") + " (expected 4)");
        System.out.println(solution.longestCommonSubstr("ab", "acb") + " (expected 1)");
    }
}
