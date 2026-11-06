package DynamicProgramming.Blind_180.D_Interval.B_Hard.P02_PalindromePartitioningII;

public class Ca_Tabulation {
    public int minCut(String s) {
        int n = s.length();
        int[] dp = new int[n + 1];
        dp[n] = -1;

        for(int start = n-1; start >= 0; start--){
            int currMinCuts = Integer.MAX_VALUE;
            for(int end = start; end < n; end++){
                if(isPalindrome(s, start, end)){
                    currMinCuts = Math.min(1 + dp[end+1], currMinCuts);
                }
            }
            dp[start] = currMinCuts;
        }

        return dp[0];
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
        Ca_Tabulation solution = new Ca_Tabulation();
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
