package DynamicProgramming.Blind_180.C_Sequence.A_Medium;

public class LongestPalindromicSubsequence_Da_TabulationSpaceOptimisation {
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
}
