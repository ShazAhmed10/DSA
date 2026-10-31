package DynamicProgramming.Blind_180.C_Sequence.A_Medium.P03_LongestPalindromicSubsequence;

public class Da_TabulationSpaceOptimisation {
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[] ahead = new int[n];
        int[] curr = new int[n];

        for(int start = n-1; start >= 0; start--){
            curr[start] = 1;
            char a = s.charAt(start);
            for(int end = start+1; end < n; end++){
                char b = s.charAt(end);
                if(a == b){
                    curr[end] = 2 + ahead[end - 1];
                }
                else{
                    curr[end] = Math.max(ahead[end], curr[end - 1]);
                }
            }
            int[] temp = ahead;
            ahead = curr;
            curr = temp;
        }

        return ahead[n-1];
    }

    public static void main(String[] args) {
        Da_TabulationSpaceOptimisation solution = new Da_TabulationSpaceOptimisation();
        System.out.println(solution.longestPalindromeSubseq("bbbab") + " (expected 4)");
        System.out.println(solution.longestPalindromeSubseq("cbbd") + " (expected 2)");
        System.out.println(solution.longestPalindromeSubseq("a") + " (expected 1)");
    }
}
