package DynamicProgramming.Blind_180.D_Interval.B_Hard.P02_PalindromePartitioningII;

public class Dc_TabulationSpaceOptimisation_1Array {
    public int minCut(String s) {
        int n = s.length();
        int[] cutsDp = new int[n+1];
        boolean[] palDp = new boolean[n];

        cutsDp[n] = -1;
        for(int start = n-1; start >= 0; start--){  
            int ans = Integer.MAX_VALUE;
            char c1 = s.charAt(start);

            for(int end = n-1; end >= start; end--){
                char c2 = s.charAt(end);

                if(c1 == c2){
                    int innerLen = (end - 1) - (start + 1) + 1;
                    if(innerLen <= 0){
                        palDp[end] = true;
                    }
                    else{
                        palDp[end] = palDp[end - 1];
                    }
                }
                else{
                    palDp[end] = false;
                }

                if(palDp[end]){
                    ans = Math.min(1 + cutsDp[end + 1], ans);
                }
            }

            cutsDp[start] = ans;
        }

        return cutsDp[0];
    }

    public static void main(String[] args) {
        Dc_TabulationSpaceOptimisation_1Array solution = new Dc_TabulationSpaceOptimisation_1Array();
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
