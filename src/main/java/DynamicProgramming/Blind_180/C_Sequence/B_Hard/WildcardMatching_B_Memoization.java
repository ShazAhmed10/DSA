package DynamicProgramming.Blind_180.C_Sequence.B_Hard;

public class WildcardMatching_B_Memoization {
    public boolean isMatch(String s, String p) {
        int n = s.length();
        int m = p.length();
        Boolean[][] dp = new Boolean[n+1][m+1];

        return helper(s, p, n, m, dp);
    }

    public boolean helper(String s, String p, int n, int m, Boolean[][] dp){
        if(n == 0 && m == 0){
            return dp[n][m] = true;
        }

        if(m == 0){
            return dp[n][m] = false;
        }

        if(dp[n][m] != null){
            return dp[n][m];
        }

        if(n == 0){
            if(p.charAt(m-1) == '*'){
                return dp[n][m] = helper(s, p, n, m-1, dp);
            }
            return dp[n][m] = false;
        }

        char c1 = s.charAt(n-1);
        char c2 = p.charAt(m-1);
        boolean curr = false;
        if(c1 == c2 || c2 == '?'){
            curr = helper(s, p, n-1, m-1, dp);
        }
        else{
            if(c2 == '*'){
                curr = helper(s, p, n, m-1, dp) || helper(s, p, n-1, m, dp);
            }
        }

        return dp[n][m] = curr;
    }
}
