package DynamicProgramming.Blind_180.C_Sequence.A_Medium;

public class EditDistance_Db_TabulationSpaceOptimisation {
    public int minDistance(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();
        int[] dp = new int[m+1];

        for(int c=1; c<=m; c++){
            dp[c] = c;
        }

        for(int i=1; i<=n; i++){
            char c1 = word1.charAt(i-1);
            int prevRowLeft = dp[0];
            dp[0] = i;

            for(int j=1; j<=m; j++){
                char c2 = word2.charAt(j-1);
                int currDistance = 0;
                int temp = dp[j];

                if(c1 == c2){
                    currDistance = prevRowLeft;
                }
                else{
                    int insert = dp[j-1];
                    int delete = dp[j];
                    int replace = prevRowLeft;

                    currDistance = 1 + Math.min(Math.min(insert, delete), replace);
                }

                dp[j] = currDistance;
                prevRowLeft = temp;
            }
        }

        return dp[m];
    }
}
