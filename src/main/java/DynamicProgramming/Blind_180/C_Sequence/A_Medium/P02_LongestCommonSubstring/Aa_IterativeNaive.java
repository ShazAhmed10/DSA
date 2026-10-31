package DynamicProgramming.Blind_180.C_Sequence.A_Medium.P02_LongestCommonSubstring;

public class Aa_IterativeNaive {
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

    public static void main(String[] args) {
        Aa_IterativeNaive solution = new Aa_IterativeNaive();
        System.out.println(solution.longestCommonSubstr("abcde", "abfce") + " (expected 2)");
        System.out.println(solution.longestCommonSubstr("abcdxyz", "xyzabcd") + " (expected 4)");
        System.out.println(solution.longestCommonSubstr("ab", "acb") + " (expected 1)");
    }
}
