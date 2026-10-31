package DynamicProgramming.Blind_180.C_Sequence.A_Medium.P03_LongestPalindromicSubsequence;

public class Db_TabulationSpaceOptimisation {
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[] dp = new int[n];

        for(int start = n-1; start >= 0; start--){
            dp[start] = 1;
            char a = s.charAt(start);
            int aheadRowLeft = 0;
            for(int end = start+1; end < n; end++){
                char b = s.charAt(end);
                int temp = dp[end];
                if(a == b){
                    dp[end] = 2 + aheadRowLeft;
                }
                else{
                    dp[end] = Math.max(dp[end], dp[end - 1]);
                }
                aheadRowLeft = temp;
            }
        }

        return dp[n-1];
    }

    public static void main(String[] args) {
        Db_TabulationSpaceOptimisation solution = new Db_TabulationSpaceOptimisation();
        System.out.println(solution.longestPalindromeSubseq("bbbab") + " (expected 4)");
        System.out.println(solution.longestPalindromeSubseq("cbbd") + " (expected 2)");
        System.out.println(solution.longestPalindromeSubseq("a") + " (expected 1)");
    }
}
