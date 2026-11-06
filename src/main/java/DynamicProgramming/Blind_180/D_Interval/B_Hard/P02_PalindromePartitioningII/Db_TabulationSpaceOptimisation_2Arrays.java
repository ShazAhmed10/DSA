package DynamicProgramming.Blind_180.D_Interval.B_Hard.P02_PalindromePartitioningII;

public class Db_TabulationSpaceOptimisation_2Arrays {
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

                if(c1 == c2){
                    int innerLen = (end - 1) - (start + 1) + 1;
                    if(innerLen <= 0){
                        palCurr[end] = true;
                    }
                    else{
                        palCurr[end] = palAhead[end - 1];
                    }
                }
                else{
                    palCurr[end] = false;
                }

                if(palCurr[end]){
                    ans = Math.min(1 + cutsDp[end + 1], ans);
                }
            }

            cutsDp[start] = ans;

            boolean[] temp = palAhead;
            palAhead = palCurr;
            palCurr = temp;
        }

        return cutsDp[0];
    }

    public static void main(String[] args) {
        Db_TabulationSpaceOptimisation_2Arrays solution = new Db_TabulationSpaceOptimisation_2Arrays();
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
