package DynamicProgramming.Blind_180.C_Sequence.A_Medium.P02_LongestCommonSubstring;

public class Da_TabulationSpaceOptimisation {
    public int longestCommonSubstr(String str1, String str2) {
        int n = str1.length();
        int m = str2.length();
        int best = 0;
        int[] ahead = new int[m+1];
        int[] curr = new int[m+1];


        for(int i=n-1; i>=0; i--){
            char a = str1.charAt(i);
            for(int j=m-1; j>=0; j--){
                char b = str2.charAt(j);
                if(a == b){
                    curr[j] = 1 + ahead[j+1];
                }
                else{
                    curr[j] = 0;
                }
                best = Math.max(best, curr[j]);
            }
            int[] temp = ahead;
            ahead = curr;
            curr = temp;
        }

        return best;
    }

    public static void main(String[] args) {
        Da_TabulationSpaceOptimisation solution = new Da_TabulationSpaceOptimisation();
        System.out.println(solution.longestCommonSubstr("abcde", "abfce") + " (expected 2)");
        System.out.println(solution.longestCommonSubstr("abcdxyz", "xyzabcd") + " (expected 4)");
        System.out.println(solution.longestCommonSubstr("ab", "acb") + " (expected 1)");
    }
}
