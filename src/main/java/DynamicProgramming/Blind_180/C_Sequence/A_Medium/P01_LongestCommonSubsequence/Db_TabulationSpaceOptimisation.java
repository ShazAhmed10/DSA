package DynamicProgramming.Blind_180.C_Sequence.A_Medium.P01_LongestCommonSubsequence;

public class Db_TabulationSpaceOptimisation {
    public int longestCommonSubsequence(String text1, String text2) {
        int n = text1.length();
        int m = text2.length();
        int[] dp = new int[m+1];

        for(int i=1; i<=n; i++){
            char a = text1.charAt(i-1);
            int prevRowLeft = 0;

            for(int j=1; j<=m; j++){
                char b = text2.charAt(j-1);
                int temp = dp[j];

                if(a == b){
                    dp[j] = 1 + prevRowLeft;
                }
                else{
                    dp[j] = Math.max(dp[j], dp[j-1]);
                }

                prevRowLeft = temp;
            }
        }

        return dp[m];
    }

    public static void main(String[] args) {
        Db_TabulationSpaceOptimisation solution = new Db_TabulationSpaceOptimisation();
        System.out.println(solution.longestCommonSubsequence("abcde", "ace") + " (expected 3)");
        System.out.println(solution.longestCommonSubsequence("abc", "abc") + " (expected 3)");
        System.out.println(solution.longestCommonSubsequence("abc", "def") + " (expected 0)");
        System.out.println(solution.longestCommonSubsequence("ab", "bb") + " (expected 1)");
    }
}
