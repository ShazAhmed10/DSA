package DynamicProgramming.Blind_180.C_Sequence.A_Medium;

public class LongestPalindromicSubsequence_A_RecursiveNaive {
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int start = 0;
        int end = n-1;

        return helper(s, start, end);
    }

    public int helper(String s, int start, int end){
        if(start > end){
            return 0;
        }

        if(start == end){
            return 1;
        }

        char a = s.charAt(start);
        char b = s.charAt(end);
        int currLength = 0;
        if(a == b){
            currLength = 2 + helper(s, start + 1, end - 1);
        }
        else{
            currLength = Math.max(helper(s, start + 1, end), helper(s, start, end - 1));
        }

        return currLength;
    }
}
