package DynamicProgramming.Blind_180.D_Interval.B_Hard.P02_PalindromePartitioningII;

public class A_RecursiveNaive {
    public int minCut(String s) {
        return helper(s, 0);
    }

    public int helper(String s, int start){
        if(start == s.length()){
            return -1;
        }

        int currMinCuts = Integer.MAX_VALUE;
        for(int end = start; end < s.length(); end++){
            if(isPalindrome(s, start, end)){
                currMinCuts = Math.min(1 + helper(s, end+1), currMinCuts);
            }
        }

        return currMinCuts;
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
        A_RecursiveNaive solution = new A_RecursiveNaive();
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
