package DynamicProgramming.Blind_180.D_Interval.B_Hard.P02_PalindromePartitioningII;

import java.util.Arrays;

public class Ba_Memoization {
    public int minCut(String s) {
        int[] dp = new int[s.length()];
        Arrays.fill(dp, -1);
        return helper(s, 0, dp);
    }

    public int helper(String s, int start, int[] dp){
        if(start == s.length()){
            return -1;
        }

        if(dp[start] != -1){
            return dp[start];
        }

        int currMinCuts = Integer.MAX_VALUE;
        for(int end = start; end < s.length(); end++){
            if(isPalindrome(s, start, end)){
                currMinCuts = Math.min(1 + helper(s, end+1, dp), currMinCuts);
            }
        }

        return dp[start] = currMinCuts;
    }

    public boolean isPalindrome(String s, int start, int end){
        if(start >= end){
            return true;
        }
        
        char c1 = s.charAt(start);
        char c2 = s.charAt(end);
        if(c1 == c2){
            return isPalindrome(s, start+1, end-1);
        }

        return false;
    }

    public static void main(String[] args) {
        Ba_Memoization solution = new Ba_Memoization();
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
