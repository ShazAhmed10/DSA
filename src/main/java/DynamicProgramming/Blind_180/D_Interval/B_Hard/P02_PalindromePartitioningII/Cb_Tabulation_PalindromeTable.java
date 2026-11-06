package DynamicProgramming.Blind_180.D_Interval.B_Hard.P02_PalindromePartitioningII;

public class Cb_Tabulation_PalindromeTable {
    public int minCut(String s) {
        int n = s.length();
        int[] cutsDp = new int[n+1];
        boolean[][] palDp = new boolean[n][n];

        for(int start = 0; start < n; start++){
            palDp[start][start] = true;
        }

        for(int start = n-1; start >= 0; start--){
            char c1 = s.charAt(start);

            for(int end = start + 1; end < n; end++){
                char c2 = s.charAt(end);
                int len = end - start + 1;

                if(len == 2){
                    if(c1 == c2){
                        palDp[start][end] = true;
                    }
                }
                else{
                    if(c1 == c2){
                        palDp[start][end] = palDp[start + 1][end - 1];
                    }
                }
            }
        }

        cutsDp[n] = -1;
        for(int start = n-1; start >= 0; start--){  
            int ans = Integer.MAX_VALUE;

            for(int end = start; end < n; end++){
                if(palDp[start][end]){
                    ans = Math.min(1 + cutsDp[end + 1], ans);
                }
            }

            cutsDp[start] = ans;
        }

        return cutsDp[0];
    }

    public static void main(String[] args) {
        Cb_Tabulation_PalindromeTable solution = new Cb_Tabulation_PalindromeTable();
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
