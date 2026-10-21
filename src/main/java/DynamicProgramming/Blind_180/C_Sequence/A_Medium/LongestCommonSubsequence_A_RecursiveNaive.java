package DynamicProgramming.Blind_180.C_Sequence.A_Medium;

public class LongestCommonSubsequence_A_RecursiveNaive {
    public int longestCommonSubsequence(String text1, String text2) {
        int n = text1.length();
        int m = text2.length();

        return helper(text1, text2, n, m);
    }

    public int helper(String text1, String text2, int n, int m){
        if(m == 0 || n == 0){
            return 0;
        }

        char a = text1.charAt(n-1);
        char b = text2.charAt(m-1);
        if(a == b){
            return 1 + helper(text1, text2, n-1, m-1);
        }
        else{
            return Math.max(helper(text1, text2, n-1, m), helper(text1, text2, n, m-1));
        }
    }
}
