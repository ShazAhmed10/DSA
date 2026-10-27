package DynamicProgramming.Blind_180.C_Sequence.A_Medium;

public class EditDistance_Da_TabulationSpaceOptimisation {
    public int minDistance(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();
        int[] prev = new int[m+1];
        int[] curr = new int[m+1];

        for(int c=1; c<=m; c++){
            prev[c] = c;
        }

        for(int i=1; i<=n; i++){
            char c1 = word1.charAt(i-1);

            curr[0] = i;
            for(int j=1; j<=m; j++){
                int currDistance = 0;
                char c2 = word2.charAt(j-1);

                if(c1 == c2){
                    currDistance = prev[j-1];
                }
                else{
                    int insert = curr[j-1];
                    int delete = prev[j];
                    int replace = prev[j-1];

                    currDistance = 1 + Math.min(Math.min(insert, delete), replace);
                }

                curr[j] = currDistance;
            }

            int[] temp = prev;
            prev = curr;
            curr = temp;
        }

        return prev[m];
    }
}
