package DynamicProgramming.Blind_180.C_Sequence.A_Medium;

public class LongestCommonSubsequence_Da_TabulationSpaceOptimisation {
    public int longestCommonSubsequence(String text1, String text2) {
        int n = text1.length();
        int m = text2.length();
        int[] prev = new int[m+1];
        int[] curr = new int[m+1];

        for(int i=1; i<=n; i++){
            char a = text1.charAt(i-1);

            for(int j=1; j<=m; j++){
                char b = text2.charAt(j-1);
                if(a == b){
                    curr[j] = 1 + prev[j-1];
                }
                else{
                    curr[j] = Math.max(prev[j], curr[j-1]);
                }
            }

            int[] temp = prev;
            prev = curr;
            curr = temp;
        }

        return prev[m];
    }
}
