package DynamicProgramming.Blind_180.C_Sequence.B_Hard.P02_WildcardMatching;

public class Da_Tabulation_2ArraySpaceOptimisation {
    public boolean isMatch(String s, String p) {
        int n = s.length();
        int m = p.length();
        boolean[] prev = new boolean[m+1];
        boolean[] curr = new boolean[m+1];

        prev[0] = true;

        for(int c=1; c<=m; c++){
            char ch = p.charAt(c-1);
            if(ch == '*'){
                prev[c] = prev[c-1];
            }
        }

        for(int r=1; r<=n; r++){
            char ch1 = s.charAt(r-1);

            for(int c=1; c<=m; c++){
                char ch2 = p.charAt(c-1);

                if(ch1 == ch2 || ch2 == '?'){
                    curr[c] = prev[c-1];
                }
                else if(ch2 == '*'){
                    curr[c] = curr[c-1] || prev[c];
                }
                else{
                    curr[c] = false;
                }
            }

            boolean[] temp = prev;
            prev = curr;
            curr = temp;

            if(r == 1){
                curr[0] = false;
            }
        }

        return prev[m];
    }

    public static void main(String[] args) {
        Da_Tabulation_2ArraySpaceOptimisation solution = new Da_Tabulation_2ArraySpaceOptimisation();
        System.out.println(solution.isMatch("aa", "a") + " (expected false)");
        System.out.println(solution.isMatch("aa", "*") + " (expected true)");
        System.out.println(solution.isMatch("cb", "?a") + " (expected false)");
        System.out.println(solution.isMatch("adceb", "*a*b") + " (expected true)");
        System.out.println(solution.isMatch("aaa", "a") + " (expected false)");
        System.out.println(solution.isMatch("aab", "*a") + " (expected false)");
    }
}
