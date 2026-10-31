package DynamicProgramming.Blind_180.C_Sequence.A_Medium.P05_EditDistance;

public class C_Tabulation {
    public int minDistance(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();
        int[][] dp = new int[n+1][m+1];

        for(int c=1; c<=m; c++){
            dp[0][c] = c;
        }

        for(int r=1; r<=n; r++){
            dp[r][0] = r;
        }

        for(int i=1; i<=n; i++){
            char c1 = word1.charAt(i-1);

            for(int j=1; j<=m; j++){
                int currDistance = 0;
                char c2 = word2.charAt(j-1);

                if(c1 == c2){
                    currDistance = dp[i-1][j-1];
                }
                else{
                    int insert = dp[i][j-1];
                    int delete = dp[i-1][j];
                    int replace = dp[i-1][j-1];

                    currDistance = 1 + Math.min(Math.min(insert, delete), replace);
                }

                dp[i][j] = currDistance;
            }
        }

        return dp[n][m];
    }

    public static void main(String[] args) {
        C_Tabulation solution = new C_Tabulation();
        System.out.println(solution.minDistance("horse", "ros") + " (expected 3)");
        System.out.println(solution.minDistance("intention", "execution") + " (expected 5)");
        System.out.println(solution.minDistance("", "abc") + " (expected 3)");
    }
}
