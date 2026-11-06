package DynamicProgramming.Blind_180.D_Interval.B_Hard.P02_PalindromePartitioningII;

import java.util.Arrays;

public class Da_TabulationSpaceOptimisation_2Arrays {
    public int minCut(String s) {
        int n = s.length();
        int[] cutsDp = new int[n+1];
        boolean[] palAhead = new boolean[n];
        boolean[] palCurr = new boolean[n];

        cutsDp[n] = -1;
        for(int start = n-1; start >= 0; start--){  
            int ans = Integer.MAX_VALUE;
            char c1 = s.charAt(start);

            for(int end = start; end < n; end++){
                char c2 = s.charAt(end);
                int len = end - start + 1;

                if(len == 1){
                    palCurr[end] = true;
                }
                else if(len == 2){
                    if(c1 == c2){
                        palCurr[end] = true;
                    }
                }
                else{
                    if(c1 == c2){
                        palCurr[end] = palAhead[end - 1];
                    }
                }

                if(palCurr[end]){
                    ans = Math.min(1 + cutsDp[end + 1], ans);
                }
            }

            cutsDp[start] = ans;

            boolean[] temp = palAhead;
            palAhead = palCurr;
            palCurr = temp;
            Arrays.fill(palCurr, false);
        }

        return cutsDp[0];
    }

    public static void main(String[] args) {
        Da_TabulationSpaceOptimisation_2Arrays solution = new Da_TabulationSpaceOptimisation_2Arrays();
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
