package DynamicProgramming.Blind_180.D_Interval.B_Hard.P02_PalindromePartitioningII;

import java.util.Arrays;

public class Bb_Memoization_PalindromeDp {
    public int minCut(String s) {
        int n = s.length();
        int[] dp = new int[n];
        Arrays.fill(dp, -1);

        Boolean[][] palDp = new Boolean[n][n];

        return helper(s, 0, dp, palDp);
    }

    public int helper(String s, int start, int[] dp, Boolean[][] palDp){
        if(start == s.length()){
            return -1;
        }

        if(dp[start] != -1){
            return dp[start];
        }

        int currMinCuts = Integer.MAX_VALUE;
        for(int end = start; end < s.length(); end++){
            if(isPalindrome(s, start, end, palDp)){
                currMinCuts = Math.min(1 + helper(s, end+1, dp, palDp), currMinCuts);
            }
        }

        return dp[start] = currMinCuts;
    }

    public boolean isPalindrome(String s, int start, int end, Boolean[][] palDp){
        if(start >= end){
            return palDp[start][end] = true;
        }

        if(palDp[start][end] != null){
            return palDp[start][end];
        }
        
        char c1 = s.charAt(start);
        char c2 = s.charAt(end);
        if(c1 == c2){
            return palDp[start][end] = isPalindrome(s, start + 1, end - 1, palDp);
        }

        return palDp[start][end] = false;
    }

    public static void main(String[] args) {
        Bb_Memoization_PalindromeDp solution = new Bb_Memoization_PalindromeDp();
        System.out.println(solution.minCut("aab") + " (expected 1)");
        System.out.println(solution.minCut("a") + " (expected 0)");
        System.out.println(solution.minCut("ab") + " (expected 1)");
        System.out.println(solution.minCut("aa") + " (expected 0)");
        System.out.println(solution.minCut("aaba") + " (expected 1)");
        System.out.println(solution.minCut("abb") + " (expected 1)");
        System.out.println(solution.minCut("aba") + " (expected 0)");
        System.out.println(solution.minCut("abbab") + " (expected 1)");
    }
}
