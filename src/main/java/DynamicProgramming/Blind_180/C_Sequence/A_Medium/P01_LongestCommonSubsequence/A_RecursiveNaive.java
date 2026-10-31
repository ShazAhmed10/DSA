package DynamicProgramming.Blind_180.C_Sequence.A_Medium.P01_LongestCommonSubsequence;

public class A_RecursiveNaive {
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

    public static void main(String[] args) {
        A_RecursiveNaive solution = new A_RecursiveNaive();
        System.out.println(solution.longestCommonSubsequence("abcde", "ace") + " (expected 3)");
        System.out.println(solution.longestCommonSubsequence("abc", "abc") + " (expected 3)");
        System.out.println(solution.longestCommonSubsequence("abc", "def") + " (expected 0)");
        System.out.println(solution.longestCommonSubsequence("ab", "bb") + " (expected 1)");
    }
}
