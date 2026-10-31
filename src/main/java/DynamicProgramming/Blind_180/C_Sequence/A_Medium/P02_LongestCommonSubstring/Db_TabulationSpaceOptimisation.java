package DynamicProgramming.Blind_180.C_Sequence.A_Medium.P02_LongestCommonSubstring;

public class Db_TabulationSpaceOptimisation {
    public int longestCommonSubstr(String str1, String str2) {
        int n = str1.length();
        int m = str2.length();
        int best = 0;
        int[] dp = new int[m+1];

        for(int i=n-1; i>=0; i--){
            char a = str1.charAt(i);
            for(int j=0; j<m; j++){
                char b = str2.charAt(j);
                if(a == b){
                    dp[j] = 1 + dp[j+1];
                }
                else{
                    dp[j] = 0;
                }
                best = Math.max(best, dp[j]);
            }
        }

        return best;
    }

    public static void main(String[] args) {
        Db_TabulationSpaceOptimisation solution = new Db_TabulationSpaceOptimisation();
        System.out.println(solution.longestCommonSubstr("abcde", "abfce") + " (expected 2)");
        System.out.println(solution.longestCommonSubstr("abcdxyz", "xyzabcd") + " (expected 4)");
        System.out.println(solution.longestCommonSubstr("ab", "acb") + " (expected 1)");
    }
}
