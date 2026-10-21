package DynamicProgramming.Blind_180.C_Sequence.A_Medium;

public class LongestCommonSubstring_Aa_IterativeNaive {
    public int longestCommonSubstr(String str1, String str2) {
        int n = str1.length();
        int m = str2.length();
        int best = 0;

        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                int len = 0;
                while(i+len < n && j+len < m && str1.charAt(i+len) == str2.charAt(j+len)){
                    len++;
                }

                best = Math.max(best, len);
            }
        }

        return best;
    }
}
