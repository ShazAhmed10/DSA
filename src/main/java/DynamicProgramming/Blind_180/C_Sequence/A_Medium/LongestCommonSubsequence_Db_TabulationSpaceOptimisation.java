package DynamicProgramming.Blind_180.C_Sequence.A_Medium;

public class LongestCommonSubsequence_Db_TabulationSpaceOptimisation {
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
}
