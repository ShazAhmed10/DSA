package DynamicProgramming.Blind_180.C_Sequence.B_Hard.P02_WildcardMatching;

public class C_Tabulation {
    public boolean isMatch(String s, String p) {
        int n = s.length();
        int m = p.length();
        boolean[][] dp = new boolean[n+1][m+1];

        dp[0][0] = true;

        for(int c=1; c<=m; c++){
            char ch = p.charAt(c-1);
            if(ch == '*'){
                dp[0][c] = dp[0][c-1];
            }
        }

        for(int r=1; r<=n; r++){
            char ch1 = s.charAt(r-1);

            for(int c=1; c<=m; c++){
                char ch2 = p.charAt(c-1);

                if(ch1 == ch2 || ch2 == '?'){
                    dp[r][c] = dp[r-1][c-1];
                }
                else{
                    if(ch2 == '*'){
                        dp[r][c] = dp[r][c-1] || dp[r-1][c];
                    }
                }
            }
        }

        return dp[n][m];
    }

    public static void main(String[] args) {
        C_Tabulation solution = new C_Tabulation();
        System.out.println(solution.isMatch("aa", "a") + " (expected false)");
        System.out.println(solution.isMatch("aa", "*") + " (expected true)");
        System.out.println(solution.isMatch("cb", "?a") + " (expected false)");
        System.out.println(solution.isMatch("adceb", "*a*b") + " (expected true)");
        System.out.println(solution.isMatch("aaa", "a") + " (expected false)");
        System.out.println(solution.isMatch("aab", "*a") + " (expected false)");
    }
}
