package DynamicProgramming.Blind_180.C_Sequence.B_Hard.P02_WildcardMatching;

public class Db_Tabulation_1ArraySpaceOptimisation {
    public boolean isMatch(String s, String p) {
        int n = s.length();
        int m = p.length();
        boolean[] dp = new boolean[m+1];

        dp[0] = true;

        for(int c=1; c<=m; c++){
            char ch = p.charAt(c-1);
            if(ch == '*'){
                dp[c] = dp[c-1];
            }
        }

        boolean prevArrLeft = false;
        for(int r=1; r<=n; r++){
            char ch1 = s.charAt(r-1);
            prevArrLeft = dp[0];
            dp[0] = false;

            for(int c=1; c<=m; c++){
                char ch2 = p.charAt(c-1);
                boolean temp = dp[c];

                if(ch1 == ch2 || ch2 == '?'){
                    dp[c] = prevArrLeft;
                }
                else if(ch2 == '*'){
                    dp[c] = dp[c-1] || dp[c];
                }
                else{
                    dp[c] = false;
                }

                prevArrLeft = temp;
            }
        }

        return dp[m];
    }

    public static void main(String[] args) {
        Db_Tabulation_1ArraySpaceOptimisation solution = new Db_Tabulation_1ArraySpaceOptimisation();
        System.out.println(solution.isMatch("aa", "a") + " (expected false)");
        System.out.println(solution.isMatch("aa", "*") + " (expected true)");
        System.out.println(solution.isMatch("cb", "?a") + " (expected false)");
        System.out.println(solution.isMatch("adceb", "*a*b") + " (expected true)");
        System.out.println(solution.isMatch("aaa", "a") + " (expected false)");
        System.out.println(solution.isMatch("aab", "*a") + " (expected false)");
    }
}
